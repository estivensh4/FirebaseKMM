package com.estiven.firebase_auth

actual class AuthResult internal constructor(internal val android: com.google.firebase.auth.AuthResult) {
    actual val credential get() = android.credential?.let { AuthCredential(it) }
    actual val user get() = android.user?.let { FirebaseUser(it) }
    actual val additionalUserInfo get() = android.additionalUserInfo?.let { AdditionalUserInfo(it) }
}