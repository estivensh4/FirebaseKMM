package com.estiven.firebase_auth

import cocoapods.FirebaseAuth.FIRActionCodeInfo

actual class ActionCodeResult(val iOS: FIRActionCodeInfo) {
    actual val operation get() = iOS.operation
    actual val email get() = iOS.email
}