/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:51
 *
 */

package com.estivensh4.firebase_auth

import com.estivensh4.firebase_app.Firebase
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
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult
    fun signOut()
    fun useAppLanguage()
    fun useEmulator(host: String, port: Int)
}