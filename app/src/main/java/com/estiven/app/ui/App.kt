package com.estiven.app.ui

import android.app.Application
import android.content.Intent
import com.estiven.app.MainActivity
import com.estiven.app.R
import com.estiven.firebase_messaging.FirebaseService
import com.estiven.firebase_messaging.FirebaseServiceOptions

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