package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRUser

actual class FirebaseUser internal constructor(val iOS: FIRUser) {
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
}