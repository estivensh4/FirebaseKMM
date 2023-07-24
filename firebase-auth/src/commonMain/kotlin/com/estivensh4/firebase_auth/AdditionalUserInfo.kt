/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

expect class AdditionalUserInfo {
    val providerId: String?
    val username: String?
    val profile: Map<String?, Any>?
    val isNewUser: Boolean
}