/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:53
 *
 */

package com.estivensh4.firebase_crashlytics

import com.estivensh4.firebase_app.Firebase

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