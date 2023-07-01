package com.estiven.firebase_storage

import cocoapods.FirebaseStorage.FIRStorage
import cocoapods.FirebaseStorage.FIRStorageReference
import com.eygraber.uri.Uri
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError
import platform.Foundation.NSURL

actual class FirebaseStorage(private val ios: FIRStorage) {
    actual val reference: StorageReference = StorageReference(ios.reference())
}

actual typealias NativeStorageReference = FIRStorageReference

actual class StorageReference actual constructor(
    internal actual val nativeValue: NativeStorageReference
) {

    actual val reference: StorageReference = StorageReference(nativeValue)

    actual suspend fun downloadUrl(path: String): Uri? =
        nativeValue.awaitResult { reference.downloadUrl(path) }


    actual fun child(pathString: String): StorageReference =
        StorageReference(nativeValue.child(pathString))

    //actual suspend fun putFile(uri: com.eygraber.uri.Uri): TaskSnapshot = TaskSnapshot(nativeValue.putFile(uri.toAndroidUri()).await())
}


internal suspend inline fun <T, reified R> T.awaitResult(function: T.(callback: (R?, NSError?) -> Unit) -> Unit): R {
    val job = CompletableDeferred<R?>()
    function { result, error ->
        if (error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Exception(error.toString()))
        }
    }
    return job.await() as R
}


fun String.toNsURL(): NSURL {
    return NSURL(this)
}