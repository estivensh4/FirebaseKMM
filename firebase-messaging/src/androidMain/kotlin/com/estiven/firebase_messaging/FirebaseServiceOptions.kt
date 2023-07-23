package com.estiven.firebase_messaging

import android.content.Intent

data class FirebaseServiceOptions(
    val intent: Intent,
    val channelId: String,
    val smallIcon: Int,
    val largeIcon: Int,
    val colorIcon: Int,
    val appName: String,
    val autoCancel: Boolean = false,
    val vibrate: LongArray = longArrayOf(1000, 1000, 1000, 1000, 1000),
    val onNewToken: (newToken: String) -> Unit
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FirebaseServiceOptions

        if (intent != other.intent) return false
        if (channelId != other.channelId) return false
        if (smallIcon != other.smallIcon) return false
        if (largeIcon != other.largeIcon) return false
        if (colorIcon != other.colorIcon) return false
        if (appName != other.appName) return false
        if (autoCancel != other.autoCancel) return false
        if (!vibrate.contentEquals(other.vibrate)) return false
        if (onNewToken != other.onNewToken) return false

        return true
    }

    override fun hashCode(): Int {
        var result = intent.hashCode()
        result = 31 * result + channelId.hashCode()
        result = 31 * result + smallIcon
        result = 31 * result + largeIcon
        result = 31 * result + colorIcon
        result = 31 * result + appName.hashCode()
        result = 31 * result + autoCancel.hashCode()
        result = 31 * result + vibrate.contentHashCode()
        result = 31 * result + onNewToken.hashCode()
        return result
    }
}