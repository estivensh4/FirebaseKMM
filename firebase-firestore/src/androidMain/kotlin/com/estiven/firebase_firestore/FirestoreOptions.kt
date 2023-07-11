package com.estiven.firebase_firestore

import com.google.firebase.FirebaseOptions

actual class FirestoreOptions(val android: FirebaseOptions) {
    actual val applicationId
        get() = android.applicationId
    actual val databaseUrl
        get() = android.databaseUrl
    actual val gaTrackingId
        get() = android.gaTrackingId
    actual val gcmSenderId
        get() = android.gcmSenderId ?: ""
    actual val storageBucket
        get() = android.storageBucket
    actual val projectId
        get() = android.projectId
}