package com.estiven.firebase_firestore

expect class FirestoreOptions {
    val applicationId: String
    val databaseUrl: String?
    val gaTrackingId: String?
    val gcmSenderId: String
    val storageBucket: String?
    val projectId: String?
}