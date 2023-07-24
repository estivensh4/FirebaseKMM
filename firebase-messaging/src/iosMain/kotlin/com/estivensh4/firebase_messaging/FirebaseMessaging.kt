package com.estivensh4.firebase_messaging

import cocoapods.FirebaseMessaging.FIRMessaging
import com.estivensh4.firebase_app.Firebase
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError

actual val Firebase.messaging
    get() = FirebaseMessaging(FIRMessaging.messaging())

actual class FirebaseMessaging(private val iOS: FIRMessaging) {
    actual val autoInitEnabled get() = iOS.autoInitEnabled
    actual fun setAutoInitEnabled(autoInitEnabled: Boolean) {
        iOS.setAutoInitEnabled(autoInitEnabled)
    }

    /**
     * Get token.
     *
     * @return
     */
    actual suspend fun getToken() = iOS.awaitResult { tokenWithCompletion(it) }

    /**
     * Subscribe to topic.
     *
     * @param topic Topic
     */
    actual suspend fun subscribeToTopic(topic: String) {
        await { iOS.subscribeToTopic(topic, it) }
    }

    /**
     * Unsubscribe from topic.
     *
     * @param topic Topic
     */
    actual suspend fun unsubscribeFromTopic(topic: String) {
        await { iOS.unsubscribeFromTopic(topic, it) }
    }
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

internal fun <T, R> T.throwError(block: T.(errorPointer: CPointer<ObjCObjectVar<NSError?>>) -> R): R {
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