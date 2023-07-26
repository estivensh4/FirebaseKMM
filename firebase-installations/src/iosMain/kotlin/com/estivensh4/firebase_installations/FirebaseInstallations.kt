/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:44
 *
 */

package com.estivensh4.firebase_installations

import cocoapods.FirebaseInstallations.FIRInstallations
import com.estivensh4.firebase_app.Firebase
import com.estivensh4.firebase_common.await
import com.estivensh4.firebase_common.awaitResult

actual val Firebase.installations
    get() = FirebaseInstallations(FIRInstallations.installations())

actual class FirebaseInstallations(private val iOS: FIRInstallations) {
    /**
     * Id.
     *
     * @return
     */
    actual suspend fun id() = iOS.awaitResult { installationIDWithCompletion(it) }

    /**
     * Delete.
     */
    actual suspend fun delete() {
        await { iOS.deleteWithCompletion(it) }
    }

    /**
     * Get token.
     *
     * @param forceRefresh Force refresh
     * @return
     */
    actual suspend fun getToken(forceRefresh: Boolean): String {
        return iOS.awaitResult { authTokenForcingRefresh(forceRefresh, it) }.authToken
    }
}