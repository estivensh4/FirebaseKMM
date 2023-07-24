package com.estivensh4.firebase_firestore

import kotlinx.coroutines.flow.Flow

expect class CollectionReference : Query {
    val id: String
    val parent: DocumentReference?
    val path: String
    val firestore: FirebaseFirestore
    fun snapshots(metadataChanges: MetadataChanges): Flow<QuerySnapshot>
    fun document(): DocumentReference
    fun document(documentPath: String): DocumentReference
    suspend fun add(data: Any): DocumentReference
    suspend fun get(): QuerySnapshot
}