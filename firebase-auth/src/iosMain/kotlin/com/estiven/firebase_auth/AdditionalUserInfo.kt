package com.estiven.firebase_auth

import cocoapods.FirebaseAuth.FIRAdditionalUserInfo

actual class AdditionalUserInfo(val iOS: FIRAdditionalUserInfo) {
    actual val providerId: String? get() = iOS.providerID
    actual val username get() = iOS.username
    @Suppress("UNCHECKED_CAST")
    actual val profile get() = iOS.profile as Map<String?, Any>?
    actual val isNewUser get() = iOS.isNewUser()
}