package com.estiven.firebase_auth

import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError

internal suspend inline fun <T, reified R> T.awaitResult(function: T.(callback: (R?, NSError?) -> Unit) -> Unit): R {
    val job = CompletableDeferred<R?>()
    function { result, error ->
        if(error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    return job.await() as R
}