/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

actual class AuthResult(internal val android: com.google.firebase.auth.AuthResult) {
    actual val credential get() = android.credential?.let { AuthCredential(it) }
    actual val user get() = android.user?.let { FirebaseUser(it) }
    actual val additionalUserInfo get() = android.additionalUserInfo?.let { AdditionalUserInfo(it) }
}