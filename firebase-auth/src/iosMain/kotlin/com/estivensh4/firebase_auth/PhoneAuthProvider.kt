/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRPhoneAuthCredential
import cocoapods.FirebaseAuth.FIRPhoneAuthProvider
import com.estivensh4.firebase_common.awaitResult

actual class PhoneAuthProvider(private val iOS: FIRPhoneAuthProvider) {

    private lateinit var auth: FirebaseAuth

    actual constructor(_auth: FirebaseAuth) : this(
        FIRPhoneAuthProvider.providerWithAuth(
            _auth.iOS
        )
    ) {
        auth = FirebaseAuth(_auth.iOS)
    }

    actual fun getCredential(
        verificationId: String,
        smsCode: String
    ) = PhoneAuthCredential(iOS.credentialWithVerificationID(verificationId, smsCode))

    actual suspend fun verifyPhoneNumber(
        phoneNumber: String,
        phoneAuthVerifyNumber: PhoneAuthVerifyNumber
    ) {
        val verificationId = iOS.awaitResult { phoneAuthProvider ->
            FIRPhoneAuthProvider.providerWithAuth(auth.iOS)
                .verifyPhoneNumber(
                    phoneNumber = phoneNumber,
                    UIDelegate = phoneAuthVerifyNumber.delete,
                    completion = phoneAuthProvider
                )
        }
        phoneAuthVerifyNumber.onCodeSent(verificationId)
    }
}

actual class PhoneAuthCredential(override val iOS: FIRPhoneAuthCredential) : AuthCredential(iOS)