package com.estiven.firebase_auth

import cocoapods.FirebaseAuth.FIRAuthCredential

actual class AuthCredential(val iOS: FIRAuthCredential) {
    actual val provider get() = iOS.provider
}