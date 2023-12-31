/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:51
 *
 */

package com.estivensh4.firebase_messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.estivensh4.firebase_app.Firebase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.tasks.await

actual val Firebase.messaging
    get() = FirebaseMessaging(com.google.firebase.messaging.FirebaseMessaging.getInstance())

actual class FirebaseMessaging(private val android: com.google.firebase.messaging.FirebaseMessaging) {
    actual val autoInitEnabled get() = android.isAutoInitEnabled
    actual fun setAutoInitEnabled(autoInitEnabled: Boolean) {
        android.isAutoInitEnabled = autoInitEnabled
    }

    /**
     * Get token.
     *
     * @return
     */
    actual suspend fun getToken(): String = android.token.await()

    /**
     * Subscribe to topic.
     *
     * @param topic Topic
     */
    actual suspend fun subscribeToTopic(topic: String) {
        android.subscribeToTopic(topic).await()
    }

    /**
     * Unsubscribe from topic.
     *
     * @param topic Topic
     */
    actual suspend fun unsubscribeFromTopic(topic: String) {
        android.subscribeToTopic(topic).await()
    }
}