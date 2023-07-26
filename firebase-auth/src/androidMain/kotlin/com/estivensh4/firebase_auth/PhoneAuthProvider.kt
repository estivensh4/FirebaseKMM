/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.coroutineScope
import com.google.firebase.auth.PhoneAuthProvider as phoneAuthProvider

actual class PhoneAuthProvider(private val android: phoneAuthProvider) {

    private lateinit var auth: FirebaseAuth

    actual constructor(_auth: FirebaseAuth) : this(
        com.google.firebase.auth.PhoneAuthProvider.getInstance(
            _auth.android
        )
    ) {
        auth = FirebaseAuth(_auth.android)
    }

    actual fun getCredential(verificationId: String, smsCode: String) =
        PhoneAuthCredential(phoneAuthProvider.getCredential(verificationId, smsCode))

    actual suspend fun verifyPhoneNumber(
        phoneNumber: String,
        phoneAuthVerifyNumber: PhoneAuthVerifyNumber
    ) = coroutineScope {
        val response = CompletableDeferred<Result<Unit>>()
        val callbacks = object :
            phoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                phoneAuthVerifyNumber.verificationCompleted(phoneAuthCredential)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                response.complete(Result.failure(exception))
            }

            override fun onCodeSent(
                verificationId: String,
                token: phoneAuthProvider.ForceResendingToken
            ) {
                phoneAuthVerifyNumber.onCodeSent(verificationId)
            }
        }
        val options = PhoneAuthOptions.newBuilder(auth.android)
            .setActivity(phoneAuthVerifyNumber.activity)
            .setPhoneNumber(phoneNumber)
            .setTimeout(phoneAuthVerifyNumber.timeout, phoneAuthVerifyNumber.timeunit)
            .setCallbacks(callbacks)
            .build()
        phoneAuthProvider.verifyPhoneNumber(options)
        response.await().getOrThrow()
    }
}

actual class PhoneAuthCredential(override val android: PhoneAuthCredential) : AuthCredential(android)
