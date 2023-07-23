package com.estiven.firebase_config

import cocoapods.FirebaseRemoteConfig.FIRRemoteConfigValue

actual class FirebaseRemoteConfigValue(private val iOS: FIRRemoteConfigValue) {
    actual val asBoolean get() = iOS.boolValue
    actual val asLong get() = iOS.numberValue.longValue
    actual val asString get() = iOS.stringValue
    actual val asDouble get() = iOS.numberValue.doubleValue
}