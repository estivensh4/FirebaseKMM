package com.estivensh4.firebase_storage

import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.OnPausedListener
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.tasks.await

actual class StorageReference(internal val android: com.google.firebase.storage.StorageReference) {
    actual val storage get() = FirebaseStorage(android.storage)
    actual val name get() = android.name
    actual val path get() = android.path
    actual val parent get() = android.parent?.let { StorageReference(it) }
    actual val bucket get() = android.bucket
    actual val root get() = StorageReference(android.root)

    /**
     * Download url.
     *
     * @return
     */
    actual suspend fun downloadUrl(): String? = android.downloadUrl.await().toString()

    /**
     * Metadata.
     *
     * @return
     */
    actual suspend fun metadata() = StorageMetadata(android.metadata.await())

    /**
     * Delete.
     *
     * @return
     */
    actual suspend fun delete() = android.delete().await().run { }

    /**
     * Child.
     *
     * @param pathString Path string
     * @return
     */
    actual fun child(pathString: String) = StorageReference(android.child(pathString))
    /**
     * Put file.
     *
     * @param file File
     * @return [Progress]
     */
    actual fun putFile(file: File): Progress {

        val uploadTask = android.putFile(file.uri)

        val flow = callbackFlow {
            val onUploadResultListener = OnProgressListener<UploadTask.TaskSnapshot> { task ->
                trySendBlocking(
                    UploadResult.Progress(
                        task.bytesTransferred,
                        task.totalByteCount
                    )
                )
            }

            val onPausedListener = OnPausedListener<UploadTask.TaskSnapshot> {
                trySendBlocking(UploadResult.Paused)
            }

            val onSuccessListener = OnSuccessListener<UploadTask.TaskSnapshot> { task ->
                trySendBlocking(
                    UploadResult.Success(
                        storage = StorageReference(task.storage),
                        error = Throwable(task.error),
                        metadata = task.metadata?.let { StorageMetadata(it) },
                        progress = UploadResult.Progress(
                            bytesTransferred = task.bytesTransferred,
                            totalByteCount = task.totalByteCount
                        )
                    )
                )
            }

            val onCanceledListener = OnCanceledListener { cancel() }
            val onFailureListener = OnFailureListener { close(it) }

            with(uploadTask) {
                addOnCanceledListener(onCanceledListener)
                addOnFailureListener(onFailureListener)
                addOnPausedListener(onPausedListener)
                addOnProgressListener(onUploadResultListener)
                addOnSuccessListener(onSuccessListener)

                awaitClose {
                    removeOnSuccessListener(onSuccessListener)
                    removeOnCanceledListener(onCanceledListener)
                    removeOnFailureListener(onFailureListener)
                    removeOnPausedListener(onPausedListener)
                    removeOnProgressListener(onUploadResultListener)
                }
            }
        }

        return object : Progress {
            override suspend fun collect(collector: FlowCollector<UploadResult>) =
                collector.emitAll(flow)

            override fun pause() = uploadTask.pause().run {}
            override fun resume() = uploadTask.resume().run {}
            override fun cancel() = uploadTask.cancel().run {}
        }
    }
}