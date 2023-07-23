package com.estiven.firebase_config

import cocoapods.FirebaseRemoteConfig.FIRRemoteConfig
import cocoapods.FirebaseRemoteConfig.FIRRemoteConfigFetchAndActivateStatus
import cocoapods.FirebaseRemoteConfig.FIRRemoteConfigSettings
import cocoapods.FirebaseRemoteConfig.FIRRemoteConfigSource
import com.estiven.firebase_app.Firebase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import platform.Foundation.NSError

actual val Firebase.config
    get() = FirebaseConfig(FIRRemoteConfig.remoteConfig())

actual class FirebaseConfig(private val iOS: FIRRemoteConfig) {

    @Suppress("UNCHECKED_CAST")
    actual val all: Map<String, FirebaseRemoteConfigValue>
        get() {
            val remoteConfigSourceList = listOf(
                FIRRemoteConfigSource.FIRRemoteConfigSourceRemote,
                FIRRemoteConfigSource.FIRRemoteConfigSourceStatic,
                FIRRemoteConfigSource.FIRRemoteConfigSourceDefault
            )
            return remoteConfigSourceList.map { configSource ->
                val keys = iOS.allKeysFromSource(configSource) as List<String>
                keys.map {
                    it to FirebaseRemoteConfigValue(
                        iOS.configValueForKey(
                            it,
                            configSource
                        )
                    )
                }
            }.flatten().toMap()
        }

    /**
     * Fetch.
     *
     * @return
     */
    actual suspend fun fetch() {
        iOS.awaitResult { fetchWithCompletionHandler(it) }
    }

    /**
     * Fetch with seconds.
     *
     * @param minimumFetchIntervalInSeconds Minimum fetch interval in seconds
     * @return
     */
    actual suspend fun fetchWithSeconds(minimumFetchIntervalInSeconds: Long) {
        iOS.awaitResult {
            fetchWithExpirationDuration(
                expirationDuration = minimumFetchIntervalInSeconds.toDouble(),
                completionHandler = it
            )
        }
    }

    /**
     * Fetch and activate.
     *
     * @return
     */
    actual suspend fun fetchAndActivate(): Boolean {
        val result = iOS.awaitResult { fetchAndActivateWithCompletionHandler(it) }
        return result == FIRRemoteConfigFetchAndActivateStatus.FIRRemoteConfigFetchAndActivateStatusSuccessFetchedFromRemote
    }

    /**
     * Settings.
     *
     * @param minimumFetchIntervalInSeconds Minimum fetch interval in seconds
     * @param fetchTimeoutInSeconds Fetch timeout in seconds
     */
    actual suspend fun settings(
        minimumFetchIntervalInSeconds: Long,
        fetchTimeoutInSeconds: Long,
    ) {
        val iosSettings = FIRRemoteConfigSettings().apply {
            setFetchTimeout(fetchTimeoutInSeconds.toDouble())
            setMinimumFetchInterval(minimumFetchIntervalInSeconds.toDouble())
        }
        iOS.setConfigSettings(iosSettings)
    }

    /**
     * Get value.
     *
     * @param key Key
     * @return
     */
    actual fun getValue(key: String) = FirebaseRemoteConfigValue(iOS.configValueForKey(key))
    @Suppress("UNCHECKED_CAST")
    actual val configUpdateListener
        get() = callbackFlow {
            val listener = iOS.addOnConfigUpdateListener { firRemoteConfigUpdate, nsError ->
                nsError?.let { close(Error(nsError.localizedDescription)) }
                firRemoteConfigUpdate?.let { trySend(firRemoteConfigUpdate.updatedKeys as List<String>) }
            }
            awaitClose { listener.remove() }
        }
}

private suspend inline fun <T, reified R> T.awaitResult(
    function: T.(callback: (R?, NSError?) -> Unit) -> Unit
): R {
    val job = CompletableDeferred<R?>()
    function { result, error ->
        if (error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Error(""))
        }
    }
    return job.await() as R
}

private suspend inline fun <T> T.await(function: T.(callback: (NSError?) -> Unit) -> Unit) {
    val job = CompletableDeferred<Unit>()
    function { error ->
        if (error == null) {
            job.complete(Unit)
        } else {
            job.completeExceptionally(Error(""))
        }
    }
    job.await()
}