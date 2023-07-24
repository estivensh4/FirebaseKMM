/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import com.google.firebase.auth.PhoneAuthCredential

actual sealed class PhoneAuthResult {
    data class VerificationCompleted(val credential: PhoneAuthCredential) : PhoneAuthResult()
    data class OnCodeSent(
        val verificationId: String,
        val token: com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
    ) : PhoneAuthResult()
}