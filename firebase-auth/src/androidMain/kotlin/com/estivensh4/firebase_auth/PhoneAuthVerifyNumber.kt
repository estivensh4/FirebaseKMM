package com.estivensh4.firebase_auth

import android.app.Activity
import java.util.concurrent.TimeUnit

actual interface PhoneAuthVerifyNumber {
    val activity: Activity
    val timeout: Long
    val timeunit: TimeUnit
}