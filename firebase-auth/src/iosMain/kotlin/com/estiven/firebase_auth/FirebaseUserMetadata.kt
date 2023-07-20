package com.estiven.firebase_auth

import cocoapods.FirebaseAuth.FIRUserMetadata

actual class FirebaseUserMetadata(val iOS: FIRUserMetadata) {
    actual val creationTimestamp get() = iOS.creationDate?.timeIntervalSinceReferenceDate?.toLong()
    actual val lastSignInTimestamp
        get() = iOS.lastSignInDate()?.timeIntervalSinceReferenceDate()?.toLong()
}