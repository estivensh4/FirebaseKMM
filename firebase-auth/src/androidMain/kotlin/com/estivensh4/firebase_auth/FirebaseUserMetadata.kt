package com.estivensh4.firebase_auth

actual class FirebaseUserMetadata(internal val android: com.google.firebase.auth.FirebaseUserMetadata) {
    actual val creationTimestamp: Long? get() = android.creationTimestamp
    actual val lastSignInTimestamp: Long? get() = android.lastSignInTimestamp
}