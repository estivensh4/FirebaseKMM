/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

actual class ActionCodeResult(private val android: com.google.firebase.auth.ActionCodeResult) {
    actual val operation get() = android.operation.toLong()
    actual val email get() = android.info?.email
}