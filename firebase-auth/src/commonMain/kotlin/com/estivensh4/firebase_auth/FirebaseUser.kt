package com.estivensh4.firebase_auth

expect class FirebaseUser {
    val phoneNumber: String?
    val isAnonymous: Boolean
    val displayName: String?
    val email: String?
    val providerId: String
    val tenantId: String?
    val uid: String
    val isEmailVerified: Boolean
    val metadata: FirebaseUserMetadata?
    val multiFactor: FirebaseMultiFactor
    val providerData: List<UserInfo>
    suspend fun getIdToken(forceRefresh: Boolean): String?
    suspend fun linkWithCredential(credential: AuthCredential): AuthResult
    suspend fun unlink(provider: String): FirebaseUser?
    suspend fun updateEmail(email: String)
    suspend fun verifyBeforeUpdateEmail(newEmail: String)
}