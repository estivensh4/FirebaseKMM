/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRActionCodeInfo

actual class ActionCodeResult(private val iOS: FIRActionCodeInfo) {
    actual val operation get() = iOS.operation
    actual val email get() = iOS.email
}