package com.estivensh4.firebase_storage

import cocoapods.FirebaseStorage.FIRStorage
import com.estivensh4.firebase_app.Firebase

actual val Firebase.storage: FirebaseStorage
    get() = FirebaseStorage(FIRStorage.storage())

actual class FirebaseStorage(private val iOS: FIRStorage) {
    actual val reference get() = StorageReference(iOS.reference())
    actual val maxDownloadRetryTime get() = iOS.maxDownloadRetryTime().toLong()
    actual val maxUploadRetryTime get() = iOS.maxUploadRetryTime().toLong()
    actual val maxOperationRetryTime get() = iOS.maxOperationRetryTime().toLong()
    actual val uploadChunkSizeBytes get() = iOS.uploadChunkSizeBytes()

    /**
     * Get reference url.
     *
     * @param url Url
     * @return
     */
    actual fun getReferenceUrl(url: String) = StorageReference(iOS.referenceForURL(url))

    /**
     * Get reference path.
     *
     * @param path Path
     * @return
     */
    actual fun getReferencePath(path: String) = StorageReference(iOS.referenceWithPath(path))

    /**
     * Set max download retry time.
     *
     * @param maxDownloadRetryTime Max download retry time
     */
    actual fun setMaxDownloadRetryTime(maxDownloadRetryTime: Long) {
        iOS.setMaxDownloadRetryTime(maxDownloadRetryTime.toDouble())
    }

    /**
     * Set max upload retry time.
     *
     * @param maxUploadRetryTime Max upload retry time
     */
    actual fun setMaxUploadRetryTime(maxUploadRetryTime: Long) {
        iOS.setMaxUploadRetryTime(maxUploadRetryTime.toDouble())
    }

    actual fun setMaxOperationRetryTime(maxOperationRetryTime: Long) {
        iOS.setMaxOperationRetryTime(maxOperationRetryTime.toDouble())
    }

    /**
     * Set upload chunk size bytes.
     *
     * @param uploadChunkSizeBytes Upload chunk size bytes
     */
    actual fun setUploadChunkSizeBytes(uploadChunkSizeBytes: Long) {
        iOS.setUploadChunkSizeBytes(uploadChunkSizeBytes)
    }

    /**
     * Use emulator.
     *
     * @param host Host
     * @param port Port
     */
    actual fun useEmulator(host: String, port: Int) {
        iOS.useEmulatorWithHost(host, port.toLong())
    }
}