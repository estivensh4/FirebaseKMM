package com.estivensh4.firebase_firestore

import cocoapods.FirebaseCore.FIROptions

actual class FirestoreOptions(private val iOS: FIROptions) {
    actual val applicationId
        get() = iOS.googleAppID
    actual val databaseUrl
        get() = iOS.databaseURL
    actual val gaTrackingId
        get() = iOS.trackingID
    actual val gcmSenderId
        get() = iOS.GCMSenderID
    actual val storageBucket
        get() = iOS.storageBucket
    actual val projectId
        get() = iOS.projectID
}