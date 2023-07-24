/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:44
 *
 */

package com.estivensh4.firebase_installations

import com.estivensh4.firebase_app.Firebase
import kotlinx.coroutines.tasks.await

actual val Firebase.installations
    get() = FirebaseInstallations(com.google.firebase.installations.FirebaseInstallations.getInstance())

actual class FirebaseInstallations(private val android: com.google.firebase.installations.FirebaseInstallations) {
    /**
     * Id.
     *
     * @return
     */
    actual suspend fun id(): String = android.id.await()

    /**
     * Delete.
     *
     * @return
     */
    actual suspend fun delete() = android.delete().await().run { }

    /**
     * Get token.
     *
     * @param forceRefresh Force refresh
     * @return
     */
    actual suspend fun getToken(forceRefresh: Boolean) =
        android.getToken(forceRefresh).await().token
}