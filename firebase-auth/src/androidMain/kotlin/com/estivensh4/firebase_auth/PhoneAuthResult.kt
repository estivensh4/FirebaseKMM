package com.estivensh4.firebase_auth

import com.google.firebase.auth.PhoneAuthCredential

actual sealed class PhoneAuthResult {
    data class VerificationCompleted(val credential: PhoneAuthCredential) : PhoneAuthResult()
    data class OnCodeSent(
        val verificationId: String,
        val token: com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
    ) : PhoneAuthResult()
}