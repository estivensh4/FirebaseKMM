package com.estiven.firebase_firestore

actual class SnapshotMetadata(val android: NativeSnapshotMetadata) {
    actual val isFromCache get() = android.isFromCache
    actual fun hasPendingWrites() = android.hasPendingWrites()
}