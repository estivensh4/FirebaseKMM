/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:09
 *
 */

package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.SnapshotMetadata

actual class SnapshotMetadata(val android: SnapshotMetadata) {
    actual val isFromCache get() = android.isFromCache
    actual fun hasPendingWrites() = android.hasPendingWrites()
}