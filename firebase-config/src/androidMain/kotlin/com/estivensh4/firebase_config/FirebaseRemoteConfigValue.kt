/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_config

actual class FirebaseRemoteConfigValue(private val android: com.google.firebase.remoteconfig.FirebaseRemoteConfigValue) {
    actual val asBoolean get() = android.asBoolean()
    actual val asLong get() = android.asLong()
    actual val asString: String? get() = android.asString()
    actual val asDouble get() = android.asDouble()
}