/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRFirestoreSettings

actual class FirestoreSettings(private val iOS: FIRFirestoreSettings) {
    actual val host
        get() = iOS.host
    actual val isSslEnabled
        get() = iOS.sslEnabled
    actual val cacheSizeBytes
        get() = iOS.cacheSizeBytes
    actual val isPersistenceEnabled
        get() = iOS.persistenceEnabled
}