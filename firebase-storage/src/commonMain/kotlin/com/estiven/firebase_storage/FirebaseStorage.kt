package com.estiven.firebase_storage

import com.estiven.firebase_app.Firebase

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