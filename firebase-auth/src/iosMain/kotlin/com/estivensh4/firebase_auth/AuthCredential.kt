/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRAuthCredential

actual open class AuthCredential(internal val iOS: FIRAuthCredential) {
    actual val provider get() = iOS.provider
}