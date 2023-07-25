/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import android.app.Activity
import com.google.firebase.auth.PhoneAuthCredential
import java.util.concurrent.TimeUnit

actual interface PhoneAuthVerifyNumber {
    val activity: Activity
    val timeout: Long
    val timeunit: TimeUnit
    fun onCodeSent(verificationId: String)
    fun verificationCompleted(credential: PhoneAuthCredential)
}