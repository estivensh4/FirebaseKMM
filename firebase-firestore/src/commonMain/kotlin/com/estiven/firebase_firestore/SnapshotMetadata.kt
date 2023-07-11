package com.estiven.firebase_firestore

expect class SnapshotMetadata {
    val isFromCache: Boolean
    fun hasPendingWrites(): Boolean
}