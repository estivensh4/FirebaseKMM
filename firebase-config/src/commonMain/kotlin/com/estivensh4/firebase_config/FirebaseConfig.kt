/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:30
 *
 */

package com.estivensh4.firebase_config

import com.estivensh4.firebase_app.Firebase
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

