/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

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