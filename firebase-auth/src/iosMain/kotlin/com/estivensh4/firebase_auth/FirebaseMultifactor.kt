/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRMultiFactor

actual class FirebaseMultiFactor(val iOS: FIRMultiFactor) {
    actual suspend fun unenroll(uid: String) = await { iOS.unenrollWithFactorUID(uid, it) }
    actual suspend fun unenrollFactorInfo(multiFactorInfo: MultiFactorInfo) =
        await { iOS.unenrollWithInfo(multiFactorInfo.iOS, it) }
}