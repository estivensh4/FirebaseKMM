package com.estiven.firebase_firestore

expect class FirestoreSettings {
    val host: String
    val isSslEnabled: Boolean
    val cacheSizeBytes: Long
    val isPersistenceEnabled: Boolean
}