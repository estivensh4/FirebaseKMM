package com.estivensh4.firebase_config

actual class FirebaseRemoteConfigValue(private val android: com.google.firebase.remoteconfig.FirebaseRemoteConfigValue) {
    actual val asBoolean get() = android.asBoolean()
    actual val asLong get() = android.asLong()
    actual val asString: String? get() = android.asString()
    actual val asDouble get() = android.asDouble()
}