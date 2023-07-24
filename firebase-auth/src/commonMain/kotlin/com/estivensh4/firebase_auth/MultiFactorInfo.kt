package com.estivensh4.firebase_auth

expect class MultiFactorInfo {
    val uid: String
    val displayName: String?
    val factorId: String
    val enrollmentTimestamp: Long
}