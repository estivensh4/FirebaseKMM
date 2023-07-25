/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

actual class FirebaseUserMetadata(private val android: com.google.firebase.auth.FirebaseUserMetadata) {
    actual val creationTimestamp: Long? get() = android.creationTimestamp
    actual val lastSignInTimestamp: Long? get() = android.lastSignInTimestamp
}