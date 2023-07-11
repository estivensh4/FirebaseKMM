package com.estiven.firebase_firestore

expect class Transaction {
    fun set(documentRef: DocumentReference, data: Any): Transaction
    fun update(
        documentRef: DocumentReference, data: MutableMap<String, Any>
    ): Transaction

    suspend fun delete(documentRef: DocumentReference)
    suspend fun get(documentRef: DocumentReference): DocumentSnapshot
}