package com.estiven.firebase_crashlytics

import com.estiven.firebase_app.Firebase

expect val Firebase.crashlytics: FirebaseCrashlytics

expect class FirebaseCrashlytics {
    fun log(message: String)
    fun deleteUnsentReports()
    fun setUserId(userId: String?)
    fun setCustomKey(key: String, value: Any)
    fun setCrashlyticsCollectionEnabled(enabled: Boolean)
    fun didCrashOnPreviousExecution(): Boolean
    fun setCustomKeys(customKeys: Map<String, Any>)
    fun recordException(exception: Throwable)

}