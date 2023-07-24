package com.estivensh4.firebase_auth

expect class AuthResult {
    val credential: AuthCredential?
    val user: FirebaseUser?
    val additionalUserInfo: AdditionalUserInfo?
}