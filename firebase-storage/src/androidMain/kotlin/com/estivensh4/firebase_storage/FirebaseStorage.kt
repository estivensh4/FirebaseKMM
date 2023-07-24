/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:49
 *
 */

package com.estivensh4.firebase_storage

import com.estivensh4.firebase_app.Firebase

actual val Firebase.storage
    get() = FirebaseStorage(com.google.firebase.storage.FirebaseStorage.getInstance())

/**
 * Firebase storage.
 *
 * @property android
 * @constructor Create [FirebaseStorage]
 */
actual class FirebaseStorage(internal val android: com.google.firebase.storage.FirebaseStorage) {
    actual val reference: StorageReference = StorageReference(android.reference)
    actual val maxDownloadRetryTime get() = android.maxDownloadRetryTimeMillis
    actual val maxUploadRetryTime get() = android.maxUploadRetryTimeMillis
    actual val maxOperationRetryTime get() = android.maxOperationRetryTimeMillis
    actual val uploadChunkSizeBytes get() = android.maxChunkUploadRetry

    /**
     * Get reference url.
     *
     * @param url Url
     * @return
     */
    actual fun getReferenceUrl(url: String) = StorageReference(android.getReferenceFromUrl(url))

    /**
     * Get reference path.
     *
     * @param path Path
     * @return
     */
    actual fun getReferencePath(path: String) = StorageReference(android.getReference(path))

    /**
     * Set max download retry time.
     *
     * @param maxDownloadRetryTime Max download retry time
     */
    actual fun setMaxDownloadRetryTime(maxDownloadRetryTime: Long) {
        android.maxDownloadRetryTimeMillis = maxDownloadRetryTime
    }

    /**
     * Set max upload retry time.
     *
     * @param maxUploadRetryTime Max upload retry time
     */
    actual fun setMaxUploadRetryTime(maxUploadRetryTime: Long) {
        android.maxUploadRetryTimeMillis = maxUploadRetryTime
    }

    /**
     * Set max operation retry time.
     *
     * @param maxOperationRetryTime Max operation retry time
     */
    actual fun setMaxOperationRetryTime(maxOperationRetryTime: Long) {
        android.maxOperationRetryTimeMillis = maxOperationRetryTime
    }

    /**
     * Set upload chunk size bytes.
     *
     * @param uploadChunkSizeBytes Upload chunk size bytes
     */
    actual fun setUploadChunkSizeBytes(uploadChunkSizeBytes: Long) {
        android.maxChunkUploadRetry = uploadChunkSizeBytes
    }

    /**
     * Use emulator.
     *
     * @param host Host
     * @param port Port
     */
    actual fun useEmulator(host: String, port: Int) {
        android.useEmulator(host, port)
    }
}