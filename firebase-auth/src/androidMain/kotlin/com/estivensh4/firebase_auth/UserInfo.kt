/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

internal fun com.google.firebase.auth.UserInfo.toAndroid(): UserInfo {
    return UserInfo(
        photoUrl = photoUrl.toString(),
        displayName = displayName,
        email = email,
        phoneNumber = phoneNumber,
        providerId = providerId,
        uid = uid,
        isEmailVerified = isEmailVerified
    )
}