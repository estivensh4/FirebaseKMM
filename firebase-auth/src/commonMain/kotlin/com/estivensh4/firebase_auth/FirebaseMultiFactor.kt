package com.estivensh4.firebase_auth

expect class FirebaseMultiFactor {
    suspend fun unenroll(uid: String)
    suspend fun unenrollFactorInfo(multiFactorInfo: MultiFactorInfo)
}