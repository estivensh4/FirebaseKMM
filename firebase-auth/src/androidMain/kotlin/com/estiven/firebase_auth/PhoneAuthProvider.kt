package com.estiven.firebase_auth

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import com.google.firebase.auth.PhoneAuthProvider as phoneAuthProvider

actual class PhoneAuthProvider(val android: phoneAuthProvider) {

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
    ) = suspendCoroutine { continuation ->
        val callbacks = object :
            phoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                continuation.resume(PhoneAuthResult.VerificationCompleted(phoneAuthCredential))
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                continuation.resumeWithException(exception)
            }

            override fun onCodeSent(
                verificationId: String,
                token: phoneAuthProvider.ForceResendingToken
            ) {
                continuation.resume(PhoneAuthResult.OnCodeSent(verificationId, token))
            }
        }
        val options = PhoneAuthOptions.newBuilder(auth.android)
            .setActivity(phoneAuthVerifyNumber.activity)
            .setPhoneNumber(phoneNumber)
            .setTimeout(phoneAuthVerifyNumber.timeout, phoneAuthVerifyNumber.timeunit)
            .setCallbacks(callbacks)
            .build()
        phoneAuthProvider.verifyPhoneNumber(options)
    }
}

actual class PhoneAuthCredential(val android: PhoneAuthCredential) {
    actual val provider get() = android.provider
}

