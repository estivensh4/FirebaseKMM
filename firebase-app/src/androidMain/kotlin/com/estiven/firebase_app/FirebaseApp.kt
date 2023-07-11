package com.estiven.firebase_app

import android.content.Context
import com.google.firebase.FirebaseApp as firebaseGoogle

actual class FirebaseApp internal constructor(private val android: firebaseGoogle) {
    actual val name: String
        get() = android.name
    actual val options: FirebaseOptions
        get() = android.options.run {
            FirebaseOptions(
                applicationId = applicationId,
                apiKey = apiKey,
                databaseUrl = databaseUrl,
                gaTrackingId = gaTrackingId,
                storageBucket = storageBucket,
                projectId = projectId
            )
        }

    actual fun delete() {}
}

actual val Firebase.app: FirebaseApp
    get() = FirebaseApp(firebaseGoogle.getInstance())

actual fun Firebase.initialize(context: Any?): FirebaseApp? =
    firebaseGoogle.initializeApp(context as Context)?.let { FirebaseApp(it) }

actual fun Firebase.initialize(context: Any?, options: FirebaseOptions): FirebaseApp? =
    FirebaseApp(firebaseGoogle.initializeApp(context as Context, options.toAndroid()))

actual fun Firebase.initialize(
    context: Any?,
    options: FirebaseOptions,
    name: String
): FirebaseApp? =
    FirebaseApp(firebaseGoogle.initializeApp(context as Context, options.toAndroid(), name))

actual fun Firebase.apps(context: Any?) =
    com.google.firebase.FirebaseApp.getApps(context as Context)
        .map { FirebaseApp(it) }


private fun FirebaseOptions.toAndroid(): com.google.firebase.FirebaseOptions {
    return com.google.firebase.FirebaseOptions.Builder()
        .setApplicationId(applicationId)
        .setApiKey(apiKey)
        .setDatabaseUrl(databaseUrl)
        .setGaTrackingId(gaTrackingId)
        .setStorageBucket(storageBucket)
        .setProjectId(projectId)
        .setGcmSenderId(gcmSenderId)
        .build()
}