/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:51
 *
 */

package com.estivensh4.firebase_config

import com.estivensh4.firebase_app.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.google.firebase.remoteconfig.FirebaseRemoteConfig as remoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings as remoteSettings

actual val Firebase.config get() = FirebaseConfig(remoteConfig.getInstance())

actual class FirebaseConfig(private val android: remoteConfig) {
    actual val all get() = android.all.mapValues { FirebaseRemoteConfigValue(it.value) }

    /**
     * Fetch.
     *
     * @return
     */
    actual suspend fun fetch() = android.fetch().await().run { }

    /**
     * Fetch with seconds.
     *
     * @param minimumFetchIntervalInSeconds Minimum fetch interval in seconds
     * @return
     */
    actual suspend fun fetchWithSeconds(minimumFetchIntervalInSeconds: Long) =
        android.fetch(minimumFetchIntervalInSeconds).await().run { }

    /**
     * Fetch and activate.
     *
     * @return
     */
    actual suspend fun fetchAndActivate(): Boolean = android.fetchAndActivate().await()

    /**
     * Settings.
     *
     * @param minimumFetchIntervalInSeconds Minimum fetch interval in seconds
     * @param fetchTimeoutInSeconds Fetch timeout in seconds
     */
    actual suspend fun settings(
        minimumFetchIntervalInSeconds: Long,
        fetchTimeoutInSeconds: Long
    ) {
        val builder = remoteSettings.Builder()
            .setFetchTimeoutInSeconds(fetchTimeoutInSeconds)
            .setMinimumFetchIntervalInSeconds(minimumFetchIntervalInSeconds)
            .build()
        android.setConfigSettingsAsync(builder).await()
    }

    /**
     * Get value.
     *
     * @param key Key
     * @return
     */
    actual fun getValue(key: String) = FirebaseRemoteConfigValue(android.getValue(key))
    actual val configUpdateListener
        get() = callbackFlow {
            val listener = android.addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    trySend(configUpdate.updatedKeys.toList())
                }

                override fun onError(error: FirebaseRemoteConfigException) {
                    close(error)
                }
            })
            awaitClose { listener.remove() }
        }
}