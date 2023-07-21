package com.estiven.firebase_auth

import cocoapods.FirebaseAuth.FIRMultiFactor

actual class FirebaseMultiFactor(val iOS: FIRMultiFactor) {
    actual suspend fun unenroll(uid: String) = await { iOS.unenrollWithFactorUID(uid, it) }
    actual suspend fun unenrollFactorInfo(multiFactorInfo: MultiFactorInfo) =
        await { iOS.unenrollWithInfo(multiFactorInfo.iOS, it) }
}