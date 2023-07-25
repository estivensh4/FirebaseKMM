/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRUser
import platform.Foundation.NSURL

actual class FirebaseUser(internal val iOS: FIRUser) {
    actual val phoneNumber get() = iOS.phoneNumber
    actual val isAnonymous get() = iOS.isAnonymous()
    actual val displayName get() = iOS.displayName
    actual val email get() = iOS.email
    actual val providerId get() = iOS.providerID
    actual val tenantId get() = iOS.tenantID
    actual val uid get() = iOS.uid
    actual val isEmailVerified get() = iOS.emailVerified
    actual val metadata: FirebaseUserMetadata? get() = FirebaseUserMetadata(iOS.metadata)
    actual val multiFactor get() = FirebaseMultiFactor(iOS.multiFactor)
    actual val photoUrl get() = iOS.photoURL?.absoluteString

    @Suppress("UNCHECKED_CAST")
    actual val providerData get() = iOS.providerData as List<UserInfo>
    actual suspend fun getIdToken(
        forceRefresh: Boolean
    ): String? = awaitResult { iOS.getIDTokenForcingRefresh(forceRefresh, it) }

    actual suspend fun linkWithCredential(
        credential: AuthCredential
    ) = AuthResult(awaitResult { iOS.linkWithCredential(credential.iOS, it) })

    actual suspend fun unlink(
        provider: String
    ): FirebaseUser? = FirebaseUser(awaitResult { iOS.unlinkFromProvider(provider, it) })

    actual suspend fun updateEmail(
        email: String
    ) = await { iOS.updateEmail(email, it) }

    actual suspend fun verifyBeforeUpdateEmail(
        newEmail: String
    ) = await { iOS.sendEmailVerificationBeforeUpdatingEmail(newEmail, it) }

    actual suspend fun updateProfile(
        displayName: String?,
        photoUrl: String?
    ) {
        val profileUpdates = iOS.profileChangeRequest()
            .apply {
                setDisplayName(displayName)
                setPhotoURL(photoUrl?.let { NSURL.URLWithString(it) })
            }
        iOS.await { profileUpdates.commitChangesWithCompletion(it) }
    }

    actual suspend fun updatePassword(newPassword: String) {
        iOS.await { updatePassword(newPassword, it) }
    }

    actual suspend fun delete() {
        iOS.await { deleteWithCompletion(it) }
    }
}