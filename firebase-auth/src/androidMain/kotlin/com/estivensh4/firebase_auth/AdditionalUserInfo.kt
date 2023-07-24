package com.estivensh4.firebase_auth

actual class AdditionalUserInfo(internal val android: com.google.firebase.auth.AdditionalUserInfo) {
    actual val providerId get() = android.providerId
    actual val username get() = android.username
    actual val profile get() = android.profile?.toMap()
    actual val isNewUser get() = android.isNewUser
}