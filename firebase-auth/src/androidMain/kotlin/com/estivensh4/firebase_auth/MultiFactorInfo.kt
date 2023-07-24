/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_auth

actual class MultiFactorInfo(internal val android: com.google.firebase.auth.MultiFactorInfo) {
    actual val uid get() = android.uid
    actual val displayName get() = android.displayName
    actual val factorId get() = android.factorId
    actual val enrollmentTimestamp get() = android.enrollmentTimestamp
}