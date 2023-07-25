/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:51
 *
 */

package com.estivensh4.firebase_auth

import com.estivensh4.firebase_app.Firebase
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuth.IdTokenListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

actual val Firebase.auth
    get() = FirebaseAuth(com.google.firebase.auth.FirebaseAuth.getInstance())

actual class FirebaseAuth internal constructor(internal val android: com.google.firebase.auth.FirebaseAuth) {

    actual val currentUser get() = android.currentUser?.let { FirebaseUser(it) }

    actual var tenantId: String?
        get() = android.tenantId
        set(value) = android.setTenantId(value ?: "")

    actual var languageCode
        get() = android.languageCode
        set(value) = android.setLanguageCode(value ?: "")

    actual val authStateListener: Flow<FirebaseAuth?>
        get() = callbackFlow {
            val listener = AuthStateListener { user -> trySend(FirebaseAuth(user)) }
            android.addAuthStateListener(listener)
            awaitClose { android.removeAuthStateListener(listener) }
        }

    actual val idTokenListener: Flow<FirebaseAuth?>
        get() = callbackFlow {
            val listener = IdTokenListener { auth -> trySend(FirebaseAuth(auth)) }
            android.addIdTokenListener(listener)
            awaitClose { android.removeIdTokenListener(listener) }
        }

    /**
     * Create user with email and password.
     *
     * @param email Email
     * @param password Password
     * @return
     */
    actual suspend fun createUserWithEmailAndPassword(
        email: String, password: String
    ) = AuthResult(android.createUserWithEmailAndPassword(email, password).await())

    /**
     * Sign in with email and password.
     *
     * @param email Email
     * @param password Password
     * @return
     */
    actual suspend fun signInWithEmailAndPassword(email: String, password: String) =
        AuthResult(android.signInWithEmailAndPassword(email, password).await())

    /**
     * Check action code.
     *
     * @param code Code
     * @return
     */
    actual suspend fun checkActionCode(code: String) =
        ActionCodeResult(android.checkActionCode(code).await())

    /**
     * Confirm password reset.
     *
     * @param code Code
     * @param newPassword New password
     * @return
     */
    actual suspend fun confirmPasswordReset(
        code: String, newPassword: String
    ) = android.confirmPasswordReset(code, newPassword).await().run { }

    /**
     * Apply action code.
     *
     * @param code Code
     * @return
     */
    actual suspend fun applyActionCode(
        code: String
    ) = android.applyActionCode(code).await().run { }

    /**
     * Verify password reset code.
     *
     * @param code Code
     * @return
     */
    actual suspend fun verifyPasswordResetCode(
        code: String
    ): String = android.verifyPasswordResetCode(code).await()

    /**
     * Sign in anonymously.
     *
     * @return
     */
    actual suspend fun signInAnonymously() = AuthResult(android.signInAnonymously().await())

    /**
     * Sign in with custom token.
     *
     * @param token Token
     * @return
     */
    actual suspend fun signInWithCustomToken(
        token: String
    ) = AuthResult(android.signInWithCustomToken(token).await())

    /**
     * Fetch sign in methods for email.
     *
     * @param email Email
     * @return
     */
    actual suspend fun fetchSignInMethodsForEmail(
        email: String
    ) = android.fetchSignInMethodsForEmail(email).await().signInMethods?.toList()

    /**
     * Update current user.
     *
     * @param user User
     * @return
     */
    actual suspend fun updateCurrentUser(
        user: FirebaseUser
    ) = android.updateCurrentUser(user.android).await().run { }

    /**
     * Is sign in with email link.
     *
     * @param link Link
     * @return
     */

    actual suspend fun isSignInWithEmailLink(
        link: String
    ) = android.isSignInWithEmailLink(link)

    /**
     * Sign in with credential.
     *
     * @param credential Credential
     * @return
     */
    actual suspend fun signInWithCredential(
        credential: AuthCredential
    ) = AuthResult(android.signInWithCredential(credential.android).await())

    /**
     * Sign out.
     *
     * @return
     */
    actual fun signOut() = android.signOut()

    /**
     * Use app language.
     *
     * @return
     */
    actual fun useAppLanguage() = android.useAppLanguage()

    /**
     * Use emulator.
     *
     * @param host Host
     * @param port Port
     * @return
     */
    actual fun useEmulator(host: String, port: Int) = android.useEmulator(host, port)

}