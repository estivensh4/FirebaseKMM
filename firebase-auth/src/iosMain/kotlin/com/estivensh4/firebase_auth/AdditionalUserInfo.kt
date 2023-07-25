/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

import cocoapods.FirebaseAuth.FIRAdditionalUserInfo

actual class AdditionalUserInfo(private val iOS: FIRAdditionalUserInfo) {
    actual val providerId: String? get() = iOS.providerID
    actual val username get() = iOS.username
    @Suppress("UNCHECKED_CAST")
    actual val profile get() = iOS.profile as Map<String?, Any>?
    actual val isNewUser get() = iOS.isNewUser()
}