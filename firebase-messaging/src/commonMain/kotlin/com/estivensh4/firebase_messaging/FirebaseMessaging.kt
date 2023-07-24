/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:54
 *
 */

package com.estivensh4.firebase_messaging

import com.estivensh4.firebase_app.Firebase

expect val Firebase.messaging: FirebaseMessaging

expect class FirebaseMessaging {
    val autoInitEnabled: Boolean
    fun setAutoInitEnabled(autoInitEnabled: Boolean)
    suspend fun getToken(): String
    suspend fun subscribeToTopic(topic: String)
    suspend fun unsubscribeFromTopic(topic: String)
}