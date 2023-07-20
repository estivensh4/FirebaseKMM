package com.estiven.firebase_auth

import com.estiven.firebase_app.Firebase
import kotlinx.coroutines.flow.Flow

expect val Firebase.auth: FirebaseAuth

expect class FirebaseAuth {
    val currentUser: FirebaseUser?
    var tenantId: String?
    var languageCode: String?
    val authStateListener: Flow<FirebaseAuth?>
    val idTokenListener: Flow<FirebaseAuth?>
    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthResult
    suspend fun checkActionCode(code: String): ActionCodeResult
    suspend fun confirmPasswordReset(code: String, newPassword: String)
    suspend fun applyActionCode(code: String)
    suspend fun verifyPasswordResetCode(code: String): String
    suspend fun signInAnonymously(): AuthResult
    suspend fun signInWithCustomToken(token: String): AuthResult
    suspend fun fetchSignInMethodsForEmail(email: String): List<String>?
    suspend fun updateCurrentUser(user: FirebaseUser)
    suspend fun isSignInWithEmailLink(link: String): Boolean
    suspend fun signInWithCredential(credential: AuthCredential): AuthResult
    fun signOut()
    fun useAppLanguage()
}