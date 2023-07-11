package com.estiven.firebase_firestore

actual class SnapshotMetadata(private val iOS: NativeSnapshotMetadata) {
    actual val isFromCache get() = iOS.isFromCache()
    actual fun hasPendingWrites() = iOS.hasPendingWrites()
}
