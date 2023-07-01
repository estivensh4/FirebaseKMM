package com.estiven.firebase_storage

import com.eygraber.uri.Uri


expect class FirebaseStorage {
    val reference: StorageReference
}

expect class NativeStorageReference

expect class StorageReference internal constructor(nativeValue: NativeStorageReference) {
    internal val nativeValue: NativeStorageReference

    val reference: StorageReference

    suspend fun downloadUrl(path: String): Uri?


    fun child(pathString: String): StorageReference

    //suspend fun putFile(uri: Uri): TaskSnapshot

}