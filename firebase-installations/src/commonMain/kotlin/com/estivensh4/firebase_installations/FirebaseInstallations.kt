/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:44
 *
 */

package com.estivensh4.firebase_installations

import com.estivensh4.firebase_app.Firebase

expect val Firebase.installations: FirebaseInstallations

expect class FirebaseInstallations {
    suspend fun id(): String
    suspend fun delete()
    suspend fun getToken(forceRefresh: Boolean): String
}