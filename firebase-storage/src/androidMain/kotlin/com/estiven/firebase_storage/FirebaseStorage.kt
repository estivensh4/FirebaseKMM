package com.estiven.firebase_storage

import com.estiven.firebase_app.Firebase
import com.eygraber.uri.Uri
import com.eygraber.uri.toUriOrNull
import kotlinx.coroutines.tasks.await


actual val Firebase.storage
    get() = FirebaseStorage(com.google.firebase.storage.FirebaseStorage.getInstance())

actual class FirebaseStorage(private val android: com.google.firebase.storage.FirebaseStorage) {
    actual val reference: StorageReference = StorageReference(android.reference)
}

actual typealias NativeStorageReference = com.google.firebase.storage.StorageReference

actual class StorageReference actual constructor(
    internal actual val nativeValue: NativeStorageReference
) {

    //actual val reference: StorageReference = StorageReference(nativeValue)

    actual suspend fun downloadUrl(path: String): Uri? {

        return  nativeValue.child(path).downloadUrl.await().run {
            toUriOrNull()
        }
    }

    //actual suspend fun putFile(uri: com.eygraber.uri.Uri): TaskSnapshot = TaskSnapshot(nativeValue.putFile(uri.toAndroidUri()).await())
}