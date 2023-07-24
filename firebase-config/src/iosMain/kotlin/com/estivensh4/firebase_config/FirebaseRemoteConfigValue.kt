/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_config

import cocoapods.FirebaseRemoteConfig.FIRRemoteConfigValue

actual class FirebaseRemoteConfigValue(private val iOS: FIRRemoteConfigValue) {
    actual val asBoolean get() = iOS.boolValue
    actual val asLong get() = iOS.numberValue.longValue
    actual val asString get() = iOS.stringValue
    actual val asDouble get() = iOS.numberValue.doubleValue
}