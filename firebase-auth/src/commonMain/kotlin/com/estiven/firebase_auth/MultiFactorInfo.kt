package com.estiven.firebase_auth

expect class MultiFactorInfo {
    val uid: String
    val displayName: String?
    val factorId: String
    val enrollmentTimestamp: Long
}