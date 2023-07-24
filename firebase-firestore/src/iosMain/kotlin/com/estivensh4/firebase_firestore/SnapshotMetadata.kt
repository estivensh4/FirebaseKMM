package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRSnapshotMetadata

actual class SnapshotMetadata(private val iOS: FIRSnapshotMetadata) {
    actual val isFromCache get() = iOS.isFromCache()
    actual fun hasPendingWrites() = iOS.hasPendingWrites()
}
