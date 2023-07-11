package com.estiven.firebase_firestore

actual class FirestoreOptions(private val iOS: NativeFirestoreOptions) {
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