package com.estiven.firebase_config

import com.estiven.firebase_app.Firebase
import kotlinx.coroutines.flow.Flow

expect val Firebase.config: FirebaseConfig

expect class FirebaseConfig {
    val all: Map<String, FirebaseRemoteConfigValue>
    actual suspend fun fetch()
    actual suspend fun fetchWithSeconds(minimumFetchIntervalInSeconds: Long)
    actual suspend fun fetchAndActivate(): Boolean
    actual suspend fun settings(
        minimumFetchIntervalInSeconds: Long,
        fetchTimeoutInSeconds: Long
    )
    actual fun getValue(key: String): FirebaseRemoteConfigValue
    actual val configUpdateListener: Flow<List<String>>
}

expect class FirebaseRemoteConfigValue {
    val asBoolean: Boolean
    val asLong: Long
    val asString: String?
    val asDouble: Double
}

