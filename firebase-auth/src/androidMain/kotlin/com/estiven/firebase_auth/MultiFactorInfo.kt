package com.estiven.firebase_auth

actual class MultiFactorInfo(val android: com.google.firebase.auth.MultiFactorInfo) {
    actual val uid get() = android.uid
    actual val displayName get() = android.displayName
    actual val factorId get() = android.factorId
    actual val enrollmentTimestamp get() = android.enrollmentTimestamp
}