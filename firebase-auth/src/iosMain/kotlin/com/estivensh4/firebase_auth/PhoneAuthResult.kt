package com.estivensh4.firebase_auth

actual sealed class PhoneAuthResult {
    data class OnCodeSent(
        val verificationId: String,
    ) : PhoneAuthResult()
}