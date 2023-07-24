package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRMultiFactorInfo

actual class MultiFactorInfo(val iOS: FIRMultiFactorInfo) {
    actual val uid get() = iOS.UID
    actual val displayName get() = iOS.displayName
    actual val factorId get() = iOS.factorID
    actual val enrollmentTimestamp get() = iOS.enrollmentDate.timeIntervalSinceReferenceDate.toLong()
}