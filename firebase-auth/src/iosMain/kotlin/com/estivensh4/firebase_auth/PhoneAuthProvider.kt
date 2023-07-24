/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRAuthCredential
import cocoapods.FirebaseAuth.FIRPhoneAuthProvider

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
    ): PhoneAuthResult {
        val verificationId = iOS.awaitResult { phoneAuthProvider ->
            FIRPhoneAuthProvider.providerWithAuth(auth.iOS)
                .verifyPhoneNumber(
                    phoneNumber = phoneNumber,
                    UIDelegate = phoneAuthVerifyNumber.delete,
                    completion = phoneAuthProvider
                )
        }
        return PhoneAuthResult.OnCodeSent(verificationId)
    }
}

actual class PhoneAuthCredential(val iOS: FIRAuthCredential) {
    actual val provider get() = iOS.provider
}