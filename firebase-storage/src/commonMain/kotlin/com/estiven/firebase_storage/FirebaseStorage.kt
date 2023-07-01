package com.estiven.firebase_storage

import com.estiven.firebase_app.Firebase
import com.eygraber.uri.Uri

expect val Firebase.storage: FirebaseStorage

expect class FirebaseStorage {
    val reference: StorageReference
}

expect class NativeStorageReference

expect class StorageReference internal constructor(nativeValue: NativeStorageReference) {
    internal val nativeValue: NativeStorageReference

    //val reference: StorageReference
    suspend fun downloadUrl(path: String): Uri?
}