/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRAuthDataResult

actual class AuthResult(val iOS: FIRAuthDataResult) {
    actual val credential get() = iOS.credential?.let { AuthCredential(it) }
    actual val user: FirebaseUser? get() = FirebaseUser(iOS.user)
    actual val additionalUserInfo get() = iOS.additionalUserInfo?.let { AdditionalUserInfo(it) }
}