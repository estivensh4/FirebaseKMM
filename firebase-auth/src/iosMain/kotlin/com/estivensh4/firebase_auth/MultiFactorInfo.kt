/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRMultiFactorInfo

actual class MultiFactorInfo(internal val iOS: FIRMultiFactorInfo) {
    actual val uid get() = iOS.UID
    actual val displayName get() = iOS.displayName
    actual val factorId get() = iOS.factorID
    actual val enrollmentTimestamp get() = iOS.enrollmentDate.timeIntervalSinceReferenceDate.toLong()
}