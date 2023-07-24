/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:53
 *
 */

package com.estivensh4.firebase_storage

import com.estivensh4.firebase_app.Firebase

expect val Firebase.storage: FirebaseStorage

expect class FirebaseStorage {
    val reference: StorageReference
    val maxDownloadRetryTime: Long
    val maxUploadRetryTime: Long
    val maxOperationRetryTime: Long
    val uploadChunkSizeBytes: Long
    fun getReferenceUrl(url: String): StorageReference
    fun getReferencePath(path: String): StorageReference
    fun setMaxDownloadRetryTime(maxDownloadRetryTime: Long)
    fun setMaxUploadRetryTime(maxUploadRetryTime: Long)
    fun setMaxOperationRetryTime(maxOperationRetryTime: Long)
    fun setUploadChunkSizeBytes(uploadChunkSizeBytes: Long)
    fun useEmulator(host: String, port: Int)
}