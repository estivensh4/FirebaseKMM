package com.estiven.firebase_storage

import com.eygraber.uri.Uri
import com.eygraber.uri.toUriOrNull
import kotlinx.coroutines.tasks.await

actual class FirebaseStorage(private val android: com.google.firebase.storage.FirebaseStorage) {
    actual val reference: StorageReference = StorageReference(android.reference)
}

actual typealias NativeStorageReference = com.google.firebase.storage.StorageReference

actual class StorageReference actual constructor(
    internal actual val nativeValue: NativeStorageReference
) {

    actual val reference: StorageReference = StorageReference(nativeValue)

    actual suspend fun downloadUrl(): Uri? = nativeValue.downloadUrl.await().toUriOrNull()
    actual fun child(pathString: String): StorageReference =
        StorageReference(nativeValue.child(pathString))

    //actual suspend fun putFile(uri: com.eygraber.uri.Uri): TaskSnapshot = TaskSnapshot(nativeValue.putFile(uri.toAndroidUri()).await())
}