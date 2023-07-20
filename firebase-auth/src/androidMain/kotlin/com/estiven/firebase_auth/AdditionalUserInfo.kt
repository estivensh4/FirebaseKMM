package com.estiven.firebase_auth

actual class AdditionalUserInfo(val android: com.google.firebase.auth.AdditionalUserInfo) {
    actual val providerId get() = android.providerId
    actual val username get() = android.username
    actual val profile get() = android.profile?.toMap()
    actual val isNewUser get() = android.isNewUser
}