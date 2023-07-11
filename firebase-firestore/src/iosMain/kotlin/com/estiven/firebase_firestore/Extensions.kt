package com.estiven.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentChangeType
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError

fun FIRDocumentChangeType.toIos(): DocumentType {
    return when (this) {
        FIRDocumentChangeType.FIRDocumentChangeTypeRemoved -> DocumentType.REMOVED
        FIRDocumentChangeType.FIRDocumentChangeTypeAdded -> DocumentType.ADDED
        FIRDocumentChangeType.FIRDocumentChangeTypeModified -> DocumentType.MODIFIED
        else -> DocumentType.ADDED
    }
}

suspend inline fun <reified T> awaitResult(function: (callback: (T?, NSError?) -> Unit) -> Unit): T {
    val job = CompletableDeferred<T?>()
    function { result, error ->
        if (error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    return job.await() as T
}

suspend inline fun <T> await(function: (callback: (NSError?) -> Unit) -> T): T {
    val job = CompletableDeferred<Unit>()
    val result = function { error ->
        if (error == null) {
            job.complete(Unit)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    job.await()
    return result
}