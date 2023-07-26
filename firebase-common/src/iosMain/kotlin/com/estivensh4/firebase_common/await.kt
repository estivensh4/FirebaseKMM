/*
 * *
 *  * Created by estiven on 26/7/23 15:19
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 26/7/23 15:19
 *
 */

package com.estivensh4.firebase_common

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError
import platform.Foundation.NSLocalizedDescriptionKey
import kotlin.native.concurrent.freeze

suspend inline fun <T, reified R> T.awaitResult(function: T.(callback: (R?, NSError?) -> Unit) -> Unit): R {
    val job = CompletableDeferred<R?>()
    function { result, error ->
        if (error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    return job.await() as R
}

suspend inline fun <T> await(function: (callback: (NSError?) -> Unit) -> T): T {
    val job = CompletableDeferred<Unit>()
    val result = function { error ->
        if (error == null) {
            job.complete(Unit)
        } else {
            job.completeExceptionally(Error(""))
        }
    }
    job.await()
    return result
}

fun <T, R> T.throwError(block: T.(errorPointer: CPointer<ObjCObjectVar<NSError?>>) -> R): R {
    memScoped {
        val errorPointer: CPointer<ObjCObjectVar<NSError?>> = alloc<ObjCObjectVar<NSError?>>().ptr
        val result = block(errorPointer)
        val error: NSError? = errorPointer.pointed.value
        if (error != null) {
            throw Exception("")
        }
        return result
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

@OptIn(FreezingIsDeprecated::class)
fun Throwable.asNSError(): NSError {
    val userInfo = mutableMapOf<Any?, Any>()
    userInfo["KotlinException"] = this.freeze()
    val message = message
    if (message != null) {
        userInfo[NSLocalizedDescriptionKey] = message
    }
    return NSError.errorWithDomain("KotlinException", 0.convert(), userInfo)
}

suspend inline fun <T> T.awaitT(function: T.(callback: (NSError?) -> Unit) -> Unit) {
    val job = CompletableDeferred<Unit>()
    function { error ->
        if(error == null) {
            job.complete(Unit)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    job.await()
}