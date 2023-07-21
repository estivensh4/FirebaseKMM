package com.estiven.firebase_auth

import kotlinx.coroutines.tasks.await

actual class FirebaseMultiFactor(internal val android: com.google.firebase.auth.MultiFactor) {

    /**
     * Unenroll.
     *
     * @param uid Uid
     * @return
     */
    actual suspend fun unenroll(uid: String) = android.unenroll(uid).await().run { }

    /**
     * Unenroll factor info.
     *
     * @param multiFactorInfo Multi factor info
     * @return
     */
    actual suspend fun unenrollFactorInfo(multiFactorInfo: MultiFactorInfo) =
        android.unenroll(multiFactorInfo.android).await().run { }
}