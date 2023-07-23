package com.estiven.firebase_messaging

import com.estiven.firebase_app.Firebase

expect val Firebase.messaging: FirebaseMessaging

expect class FirebaseMessaging {
    val autoInitEnabled: Boolean
    fun setAutoInitEnabled(autoInitEnabled: Boolean)
    suspend fun getToken(): String
    suspend fun subscribeToTopic(topic: String)
    suspend fun unsubscribeFromTopic(topic: String)
}