/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:53
 *
 */

package com.estivensh4.firebase_crashlytics

import cocoapods.FirebaseCrashlytics.FIRCrashlytics
import com.estivensh4.firebase_app.Firebase
import com.estivensh4.firebase_common.asNSError
import kotlinx.cinterop.value

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
    @Suppress("UNCHECKED_CAST")
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