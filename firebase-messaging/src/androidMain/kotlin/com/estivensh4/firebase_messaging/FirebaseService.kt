/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let { notification ->
            val title = notification.title
            val message = notification.body
            if (!title.isNullOrEmpty() && !message.isNullOrEmpty()) {
                sendNotification(title, message)
            }
        }
    }

    private fun sendNotification(title: String, message: String) {
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, options.intent, flag)
        val notificationBuilder = NotificationCompat.Builder(this, options.channelId)
            .setSmallIcon(options.smallIcon)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(applicationContext, options.colorIcon))
            .setLargeIcon(BitmapFactory.decodeResource(resources, options.largeIcon))
            .setAutoCancel(options.autoCancel)
            .setVibrate(options.vibrate)
            .setContentIntent(pendingIntent)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                options.channelId,
                options.appName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        options.onNewToken(p0)
    }

    companion object {
        private lateinit var options: FirebaseServiceOptions
        fun newInstance(
            firebaseServiceOptions: FirebaseServiceOptions
        ): FirebaseService {
            return FirebaseService().apply {
                options = firebaseServiceOptions
            }
        }
    }
}