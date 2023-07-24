package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.SnapshotMetadata

actual class SnapshotMetadata(val android: SnapshotMetadata) {
    actual val isFromCache get() = android.isFromCache
    actual fun hasPendingWrites() = android.hasPendingWrites()
}