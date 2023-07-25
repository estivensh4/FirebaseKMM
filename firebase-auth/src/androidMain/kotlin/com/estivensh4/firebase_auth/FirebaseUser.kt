/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import android.net.Uri
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

actual class FirebaseUser(internal val android: com.google.firebase.auth.FirebaseUser) {
    actual val phoneNumber get() = android.phoneNumber
    actual val isAnonymous get() = android.isAnonymous
    actual val displayName get() = android.displayName
    actual val email get() = android.email
    actual val providerId get() = android.providerId
    actual val tenantId get() = android.tenantId
    actual val uid get() = android.uid
    actual val isEmailVerified get() = android.isEmailVerified
    actual val metadata get() = android.metadata?.let { FirebaseUserMetadata(it) }
    actual val multiFactor get() = FirebaseMultiFactor(android.multiFactor)
    actual val providerData get() = android.providerData.map { it.toAndroid() }.toList()
    actual val photoUrl: String? get() = android.photoUrl?.toString()

    /**
     * Get id token.
     *
     * @param forceRefresh Force refresh
     * @return
     */
    actual suspend fun getIdToken(
        forceRefresh: Boolean
    ) = android.getIdToken(forceRefresh).await().token

    /**
     * Link with credential.
     *
     * @param credential Credential
     * @return
     */
    actual suspend fun linkWithCredential(
        credential: AuthCredential
    ) = AuthResult(android.linkWithCredential(credential.android).await())

    /**
     * Unlink.
     *
     * @param provider Provider
     * @return
     */
    actual suspend fun unlink(
        provider: String
    ) = android.unlink(providerId).await().user?.let { FirebaseUser(it) }

    /**
     * Update email.
     *
     * @param email Email
     * @return
     */
    actual suspend fun updateEmail(
        email: String
    ) = android.updateEmail(email).await().run { }

    /**
     * Verify before update email.
     *
     * @param newEmail New email
     * @return
     */
    actual suspend fun verifyBeforeUpdateEmail(
        newEmail: String
    ) = android.verifyBeforeUpdateEmail(newEmail).await().run { }

    /**
     * Update profile.
     *
     * @param displayName Display name
     * @param photoUrl Photo url
     */
    actual suspend fun updateProfile(
        displayName: String?,
        photoUrl: String?
    ) {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(displayName)
            .setPhotoUri(Uri.parse(photoUrl))
            .build()
        android.updateProfile(profileUpdates)
    }

    /**
     * Update password.
     *
     * @param newPassword New password
     */
    actual suspend fun updatePassword(newPassword: String) {
        android.updatePassword(newPassword).await()
    }

    /**
     * Delete.
     */
    actual suspend fun delete() {
        android.delete().await()
    }
}