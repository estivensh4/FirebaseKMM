/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

actual open class AuthCredential(internal open val android: com.google.firebase.auth.AuthCredential) {
    actual val provider get() = android.provider
}