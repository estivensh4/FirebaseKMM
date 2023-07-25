/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

actual class AdditionalUserInfo(private val android: com.google.firebase.auth.AdditionalUserInfo) {
    actual val providerId get() = android.providerId
    actual val username get() = android.username
    actual val profile get() = android.profile?.toMap()
    actual val isNewUser get() = android.isNewUser
}