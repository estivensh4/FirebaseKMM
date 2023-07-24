package com.estivensh4.firebase_auth

actual class AuthCredential(internal val android: com.google.firebase.auth.AuthCredential) {
    actual val provider get() = android.provider
}