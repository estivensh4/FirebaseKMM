/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:26
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.*
import com.estivensh4.firebase_app.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

actual val Firebase.auth
    get() = FirebaseAuth(FIRAuth())

actual class FirebaseAuth(val iOS: FIRAuth) {

    actual val currentUser get() = iOS.currentUser?.let { FirebaseUser(it) }

    actual var tenantId
        get() = iOS.tenantID
        set(value) = iOS.setTenantID(value)

    actual var languageCode
        get() = iOS.languageCode
        set(value) = iOS.setLanguageCode(value)

    actual val authStateListener
        get() = callbackFlow {
            val listener = iOS.addAuthStateDidChangeListener { firAuth, _ ->
                trySend(firAuth?.let { FirebaseAuth(it) })
            }
            awaitClose { iOS.removeAuthStateDidChangeListener(listener) }
        }

    actual val idTokenListener
        get() = callbackFlow {
            val listener = iOS.addIDTokenDidChangeListener { firAuth, _ ->
                trySend(firAuth?.let { FirebaseAuth(it) })
            }
            awaitClose { iOS.removeIDTokenDidChangeListener(listener) }
        }

    /**
     * Create user with email and password.
     *
     * @param email Email
     * @param password Password
     * @return
     */
    actual suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ) = AuthResult(awaitResult { iOS.createUserWithEmail(email, password, it) })

    /**
     * Check action code.
     *
     * @param code Code
     * @return
     */
    actual suspend fun checkActionCode(code: String) =
        ActionCodeResult(awaitResult { iOS.checkActionCode(code, it) })

    /**
     * Confirm password reset.
     *
     * @param code Code
     * @param newPassword New password
     * @return
     */
    actual suspend fun confirmPasswordReset(
        code: String,
        newPassword: String
    ) = await { iOS.confirmPasswordResetWithCode(code, newPassword, it) }

    /**
     * Apply action code.
     *
     * @param code Code
     * @return
     */
    actual suspend fun applyActionCode(
        code: String
    ) = await { iOS.applyActionCode(code, it) }

    /**
     * Verify password reset code.
     *
     * @param code Code
     * @return
     */
    actual suspend fun verifyPasswordResetCode(
        code: String
    ) = awaitResult { iOS.verifyPasswordResetCode(code, it) }

    /**
     * Sign in anonymously.
     *
     * @return
     */
    actual suspend fun signInAnonymously() =
        AuthResult(awaitResult { iOS.signInAnonymouslyWithCompletion(it) })

    /**
     * Sign in with custom token.
     *
     * @param token Token
     * @return
     */
    actual suspend fun signInWithCustomToken(
        token: String
    ) = AuthResult(awaitResult { iOS.signInWithCustomToken(token, it) })

    /**
     * Fetch sign in methods for email.
     *
     * @param email Email
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    actual suspend fun fetchSignInMethodsForEmail(
        email: String
    ) = awaitResult { iOS.fetchSignInMethodsForEmail(email, it) } as List<String>?

    /**
     * Update current user.
     *
     * @param user User
     * @return
     */
    actual suspend fun updateCurrentUser(
        user: FirebaseUser
    ) = await { iOS.updateCurrentUser(user.iOS, it) }

    /**
     * Is sign in with email link.
     *
     * @param link Link
     * @return
     */
    actual suspend fun isSignInWithEmailLink(
        link: String
    ) = iOS.isSignInWithEmailLink(link)

    /**
     * Sign in with credential.
     *
     * @param credential Credential
     * @return
     */
    actual suspend fun signInWithCredential(
        credential: AuthCredential
    ) = AuthResult(awaitResult { iOS.signInWithCredential(credential.iOS, it) })

    /**
     * Sign out.
     *
     * @return
     */
    actual fun signOut() = iOS.throwError { signOut(it) }.run { }

    /**
     * Use app language.
     *
     * @return
     */
    actual fun useAppLanguage() = iOS.useAppLanguage()

    actual fun useEmulator(host: String, port: Int) = iOS.useEmulatorWithHost(host, port.toLong())
}