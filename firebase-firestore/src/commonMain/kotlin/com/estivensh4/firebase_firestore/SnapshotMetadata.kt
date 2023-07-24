package com.estivensh4.firebase_firestore

expect class SnapshotMetadata {
    val isFromCache: Boolean
    fun hasPendingWrites(): Boolean
}