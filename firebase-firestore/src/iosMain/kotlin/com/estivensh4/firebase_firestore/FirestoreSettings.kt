package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRFirestoreSettings

actual class FirestoreSettings(private val iOS: FIRFirestoreSettings) {
    actual val host
        get() = iOS.host
    actual val isSslEnabled
        get() = iOS.sslEnabled
    actual val cacheSizeBytes
        get() = iOS.cacheSizeBytes
    actual val isPersistenceEnabled
        get() = iOS.persistenceEnabled
}