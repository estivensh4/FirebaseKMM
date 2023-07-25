/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

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
    val photoUrl: String?
    suspend fun getIdToken(forceRefresh: Boolean): String?
    suspend fun linkWithCredential(credential: AuthCredential): AuthResult
    suspend fun unlink(provider: String): FirebaseUser?
    suspend fun updateEmail(email: String)
    suspend fun verifyBeforeUpdateEmail(newEmail: String)
    suspend fun updateProfile(displayName: String?, photoUrl: String?)
    suspend fun updatePassword(newPassword: String)
    suspend fun delete()
}