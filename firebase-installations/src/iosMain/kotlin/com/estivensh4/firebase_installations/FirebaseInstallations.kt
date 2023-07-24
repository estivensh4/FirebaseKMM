/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:44
 *
 */

package com.estivensh4.firebase_installations

import cocoapods.FirebaseInstallations.FIRInstallations
import com.estivensh4.firebase_app.Firebase
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError

actual val Firebase.installations
    get() = FirebaseInstallations(FIRInstallations.installations())

actual class FirebaseInstallations(private val iOS: FIRInstallations) {
    /**
     * Id.
     *
     * @return
     */
    actual suspend fun id() = iOS.awaitResult { installationIDWithCompletion(it) }

    /**
     * Delete.
     */
    actual suspend fun delete() {
        await { iOS.deleteWithCompletion(it) }
    }

    /**
     * Get token.
     *
     * @param forceRefresh Force refresh
     * @return
     */
    actual suspend fun getToken(forceRefresh: Boolean): String {
        return iOS.awaitResult { authTokenForcingRefresh(forceRefresh, it) }.authToken
    }
}


private suspend inline fun <T, reified R> T.awaitResult(
    function: T.(callback: (R?, NSError?) -> Unit) -> Unit
): R {
    val job = CompletableDeferred<R?>()
    function { result, error ->
        if (error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Error(""))
        }
    }
    return job.await() as R
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