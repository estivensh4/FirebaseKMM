package com.estivensh4.firebase_crashlytics

import com.estivensh4.firebase_app.Firebase
import com.google.firebase.crashlytics.CustomKeysAndValues

actual val Firebase.crashlytics
    get() = FirebaseCrashlytics(com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance())

actual class FirebaseCrashlytics(private val android: com.google.firebase.crashlytics.FirebaseCrashlytics) {
    /**
     * Log.
     *
     * @param message Message
     */
    actual fun log(message: String) {
        android.log(message)
    }

    /**
     * Delete unsent reports.
     */
    actual fun deleteUnsentReports() {
        android.deleteUnsentReports()
    }

    /**
     * Set user id.
     *
     * @param userId User id
     */
    actual fun setUserId(userId: String?) {
        android.setUserId(userId ?: "")
    }

    /**
     * Set crashlytics collection enabled.
     *
     * @param enabled Enabled
     */
    actual fun setCrashlyticsCollectionEnabled(enabled: Boolean) {
        android.setCrashlyticsCollectionEnabled(enabled)
    }

    /**
     * Set custom key.
     *
     * @param key Key
     * @param value Value
     */
    actual fun setCustomKey(key: String, value: Any) {
        when (value) {
            is String -> android.setCustomKey(key, value)
            is Boolean -> android.setCustomKey(key, value)
            is Double -> android.setCustomKey(key, value)
            is Float -> android.setCustomKey(key, value)
            is Int -> android.setCustomKey(key, value)
            is Long -> android.setCustomKey(key, value)
        }
    }

    /**
     * Set custom keys.
     *
     * @param customKeys Custom keys
     */
    actual fun setCustomKeys(customKeys: Map<String, Any>) {
        val builder = CustomKeysAndValues.Builder()
        builder.apply {
            customKeys.forEach { (key, value) ->
                when (value) {
                    is String -> putString(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Double -> putDouble(key, value)
                    is Float -> putFloat(key, value)
                    is Int -> putInt(key, value)
                    is Long -> putLong(key, value)
                }
            }
        }
        android.setCustomKeys(builder.build())
    }

    /**
     * Record exception.
     *
     * @param exception Exception
     */
    actual fun recordException(exception: Throwable) {
        android.recordException(exception)
    }

    /**
     * Did crash on previous execution.
     *
     * @return
     */
    actual fun didCrashOnPreviousExecution() = android.didCrashOnPreviousExecution()
}