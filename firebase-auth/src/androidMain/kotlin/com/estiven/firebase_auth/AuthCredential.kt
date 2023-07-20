package com.estiven.firebase_auth

actual class AuthCredential(val android: com.google.firebase.auth.AuthCredential) {
    actual val provider get() = android.provider
}