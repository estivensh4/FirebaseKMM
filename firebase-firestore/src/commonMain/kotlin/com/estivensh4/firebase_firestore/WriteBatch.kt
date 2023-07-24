package com.estivensh4.firebase_firestore

expect class WriteBatch {
    fun set(documentRef: DocumentReference, data: Any): WriteBatch
    fun update(
        documentRef: DocumentReference, data: MutableMap<String, Any>
    ): WriteBatch

    fun delete(
        documentRef: DocumentReference
    ): WriteBatch

    suspend fun commit()
}