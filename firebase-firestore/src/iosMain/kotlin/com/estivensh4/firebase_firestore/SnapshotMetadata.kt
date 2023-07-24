/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRSnapshotMetadata

actual class SnapshotMetadata(private val iOS: FIRSnapshotMetadata) {
    actual val isFromCache get() = iOS.isFromCache()
    actual fun hasPendingWrites() = iOS.hasPendingWrites()
}
