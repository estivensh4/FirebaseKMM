/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_storage

import cocoapods.FirebaseStorage.FIRStorageReference
import cocoapods.FirebaseStorage.FIRStorageTaskStatusFailure
import cocoapods.FirebaseStorage.FIRStorageTaskStatusPause
import cocoapods.FirebaseStorage.FIRStorageTaskStatusProgress
import cocoapods.FirebaseStorage.FIRStorageTaskStatusResume
import cocoapods.FirebaseStorage.FIRStorageTaskStatusSuccess
import com.estivensh4.firebase_common.awaitResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emitAll

actual class StorageReference(private val iOS: FIRStorageReference) {
    actual val storage get() = FirebaseStorage(iOS.storage())
    actual val name get() = iOS.name()
    actual val path get() = iOS.fullPath()
    actual val parent get() = iOS.parent()?.let { StorageReference(it) }
    actual val bucket get() = iOS.bucket()
    actual val root get() = StorageReference(iOS.root())

    /**
     * Download url.
     *
     * @return
     */
    actual suspend fun downloadUrl() =
        iOS.awaitResult { downloadURLWithCompletion(it) }.absoluteString()

    /**
     * Metadata.
     *
     * @return
     */
    actual suspend fun metadata() = StorageMetadata(iOS.awaitResult { metadataWithCompletion(it) })

    /**
     * Delete.
     *
     * @return
     */
    actual suspend fun delete() = iOS.deleteWithCompletion { }.run { }

    /**
     * Child.
     *
     * @param pathString Path string
     * @return
     */
    actual fun child(pathString: String) = StorageReference(iOS.child(pathString))

    /**
     * Put file.
     *
     * @param file File
     * @return [Progress]
     */
    actual fun putFile(file: File): Progress {

        val uploadTask = iOS.putFile(file.url)

        val flow = callbackFlow {
            with(uploadTask) {
                observeStatus(FIRStorageTaskStatusResume) { task ->
                    val progress = task!!.progress()!!
                    trySendBlocking(
                        UploadResult.Progress(
                            progress.completedUnitCount,
                            progress.totalUnitCount
                        )
                    )
                }

                observeStatus(FIRStorageTaskStatusProgress) { task ->
                    val progress = task!!.progress()!!
                    trySendBlocking(
                        UploadResult.Progress(
                            progress.completedUnitCount,
                            progress.totalUnitCount
                        )
                    )
                }
                observeStatus(FIRStorageTaskStatusPause) {
                    trySendBlocking(UploadResult.Paused)
                }

                observeStatus(FIRStorageTaskStatusSuccess) { task ->
                    val progress = task!!.progress()!!
                    trySendBlocking(
                        UploadResult.Success(
                            storage = StorageReference(task.reference()),
                            error = Throwable(task.error().toString()),
                            metadata = task.metadata()?.let { StorageMetadata(it) },
                            progress = UploadResult.Progress(
                                bytesTransferred = progress.completedUnitCount,
                                totalByteCount = progress.totalUnitCount
                            )
                        )
                    )
                }
                observeStatus(FIRStorageTaskStatusFailure) {
                    close(Throwable(it?.error().toString()))
                }
                awaitClose { removeAllObservers() }
            }
        }

        return object : Progress {
            override suspend fun collect(collector: FlowCollector<UploadResult>) =
                collector.emitAll(flow)

            override fun pause() = uploadTask.pause()
            override fun resume() = uploadTask.resume()
            override fun cancel() = uploadTask.cancel()
        }
    }
}