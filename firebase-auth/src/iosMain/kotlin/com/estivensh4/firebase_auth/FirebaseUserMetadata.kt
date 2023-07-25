/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRUserMetadata

actual class FirebaseUserMetadata(private val iOS: FIRUserMetadata) {
    actual val creationTimestamp get() = iOS.creationDate?.timeIntervalSinceReferenceDate?.toLong()
    actual val lastSignInTimestamp
        get() = iOS.lastSignInDate()?.timeIntervalSinceReferenceDate()?.toLong()
}