package com.estiven.firebase_crashlytics

import cocoapods.FirebaseCrashlytics.FIRCrashlytics
import com.estiven.firebase_app.Firebase
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

actual val Firebase.crashlytics
    get() = FirebaseCrashlytics(FIRCrashlytics.crashlytics())

actual class FirebaseCrashlytics(private val iOS: FIRCrashlytics) {
    /**
     * Log.
     *
     * @param message Message
     */
    actual fun log(message: String) {
        iOS.log(message)
    }

    /**
     * Delete unsent reports.
     */
    actual fun deleteUnsentReports() {
        iOS.deleteUnsentReports()
    }

    /**
     * Set user id.
     *
     * @param userId User id
     */
    actual fun setUserId(userId: String?) {
        iOS.setUserID(userId)
    }

    /**
     * Set custom key.
     *
     * @param key Key
     * @param value Value
     */
    actual fun setCustomKey(key: String, value: Any) {
        iOS.setCustomValue(key, value.toString())
    }

    /**
     * Set crashlytics collection enabled.
     *
     * @param enabled Enabled
     */
    actual fun setCrashlyticsCollectionEnabled(enabled: Boolean) {
        iOS.setCrashlyticsCollectionEnabled(enabled)
    }

    /**
     * Set custom keys.
     *
     * @param customKeys Custom keys
     */
    actual fun setCustomKeys(customKeys: Map<String, Any>) {
        iOS.setCustomKeysAndValues(customKeys as Map<Any?, *>)
    }

    /**
     * Record exception.
     *
     * @param exception Exception
     */
    actual fun recordException(exception: Throwable) {
        iOS.recordError(exception.asNSError())
    }

    /**
     * Did crash on previous execution.
     *
     * @return
     */
    actual fun didCrashOnPreviousExecution() = iOS.didCrashDuringPreviousExecution()
}

@OptIn(FreezingIsDeprecated::class)
internal fun Throwable.asNSError(): NSError {
    val userInfo = mutableMapOf<Any?, Any>()
    userInfo["KotlinException"] = this.freeze()
    val message = message
    if (message != null) {
        userInfo[NSLocalizedDescriptionKey] = message
    }
    return NSError.errorWithDomain("KotlinException", 0.convert(), userInfo)
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