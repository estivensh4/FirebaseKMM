package com.estiven.firebase_auth

import com.estiven.firebase_app.Firebase

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