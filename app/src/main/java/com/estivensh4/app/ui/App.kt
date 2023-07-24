/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:23
 *
 */

package com.estivensh4.app.ui

import android.app.Application
import android.content.Intent
import com.estivensh4.app.MainActivity
import com.estiven.app.R
import com.estivensh4.firebase_messaging.FirebaseService
import com.estivensh4.firebase_messaging.FirebaseServiceOptions

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        FirebaseService.newInstance(
            firebaseServiceOptions = FirebaseServiceOptions(
                intent = intent,
                channelId = getString(R.string.default_notification_channel_id),
                smallIcon = R.drawable.ic_launcher_background,
                largeIcon = R.drawable.ic_launcher_foreground,
                colorIcon = R.color.purple_500,
                appName = getString(R.string.app_name)
            ) {
                print("Se ha impreso el nuevo token $it")
            }
        )
    }

}