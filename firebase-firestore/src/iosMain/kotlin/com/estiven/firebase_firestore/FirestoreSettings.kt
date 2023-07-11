package com.estiven.firebase_firestore

actual class FirestoreSettings(private val iOS: NativeFirestoreSettings) {
    actual val host
        get() = iOS.host
    actual val isSslEnabled
        get() = iOS.sslEnabled
    actual val cacheSizeBytes
        get() = iOS.cacheSizeBytes
    actual val isPersistenceEnabled
        get() = iOS.persistenceEnabled
}