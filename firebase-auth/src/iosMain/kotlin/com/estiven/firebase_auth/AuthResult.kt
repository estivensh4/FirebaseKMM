package com.estiven.firebase_auth

import cocoapods.FirebaseAuth.FIRAuthDataResult

actual class AuthResult(val iOS: FIRAuthDataResult) {
    actual val credential get() = iOS.credential?.let { AuthCredential(it) }
    actual val user: FirebaseUser? get() = FirebaseUser(iOS.user)
    actual val additionalUserInfo get() = iOS.additionalUserInfo?.let { AdditionalUserInfo(it) }
}