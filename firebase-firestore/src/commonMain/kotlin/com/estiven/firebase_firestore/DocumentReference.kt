package com.estiven.firebase_firestore

import kotlinx.coroutines.flow.Flow

expect class DocumentReference {
    val id: String
    val parent: CollectionReference
    val path: String
    val snapshotListener: Flow<DocumentSnapshot>
    val firestore: FirebaseFirestore
    suspend fun delete()
    suspend fun set(data: Any)
    suspend fun update(data: MutableMap<String, Any>)
    suspend fun get(): DocumentSnapshot
    suspend fun get(source: Source): DocumentSnapshot
    fun collection(collectionPath: String): CollectionReference
}