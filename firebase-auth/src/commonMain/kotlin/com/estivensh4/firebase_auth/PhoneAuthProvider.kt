/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:51
 *
 */

package com.estivensh4.firebase_auth

import com.estivensh4.firebase_app.Firebase

expect class PhoneAuthProvider constructor(_auth: FirebaseAuth = Firebase.auth) {

    actual fun getCredential(verificationId: String, smsCode: String): PhoneAuthCredential

    suspend fun verifyPhoneNumber(
        phoneNumber: String,
        phoneAuthVerifyNumber: PhoneAuthVerifyNumber
    ): PhoneAuthResult
}

expect class PhoneAuthCredential {
    val provider: String
}

expect sealed class PhoneAuthResult
expect interface PhoneAuthVerifyNumber