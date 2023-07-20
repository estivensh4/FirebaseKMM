package com.estiven.firebase_auth

actual class FirebaseUserMetadata(val android: com.google.firebase.auth.FirebaseUserMetadata) {
    actual val creationTimestamp: Long? get() = android.creationTimestamp
    actual val lastSignInTimestamp: Long? get() = android.lastSignInTimestamp
}