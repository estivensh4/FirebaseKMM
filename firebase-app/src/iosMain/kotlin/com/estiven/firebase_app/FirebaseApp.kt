package com.estiven.firebase_app

import cocoapods.FirebaseCore.FIRApp
import cocoapods.FirebaseCore.FIROptions

actual class FirebaseApp internal constructor(private val ios: FIRApp) {
    actual val name: String
        get() = ios.name
    actual val options: FirebaseOptions
        get() = ios.options.run {
            FirebaseOptions(
                applicationId = bundleID,
                apiKey = APIKey!!,
                databaseUrl = databaseURL!!,
                gaTrackingId = storageBucket,
                storageBucket = projectID
            )
        }

    actual fun delete() {}
}

actual val Firebase.app: FirebaseApp
    get() = FirebaseApp(FIRApp.defaultApp()!!)

actual fun Firebase.initialize(context: Any?): FirebaseApp? {
    return FIRApp.configure().let { app }
}

actual fun Firebase.initialize(
    context: Any?,
    options: FirebaseOptions
): FirebaseApp? = FIRApp.configureWithOptions(options.toIos()).let { app }

actual fun Firebase.initialize(
    context: Any?,
    options: FirebaseOptions,
    name: String
): FirebaseApp? = FIRApp.configureWithName(name, options.toIos()).let { app }


private fun FirebaseOptions.toIos(): FIROptions {
    return FIROptions(this@toIos.applicationId, this@toIos.gcmSenderId ?: "").apply {
        APIKey = this@toIos.apiKey
        databaseURL = this@toIos.databaseUrl
        trackingID = this@toIos.gaTrackingId
        storageBucket = this@toIos.storageBucket
        projectID = this@toIos.projectId
    }
}