/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

data class UserInfo(
    val photoUrl: String?,
    val displayName: String?,
    val email: String?,
    val phoneNumber: String?,
    val providerId: String?,
    val uid: String?,
    val isEmailVerified: Boolean
)