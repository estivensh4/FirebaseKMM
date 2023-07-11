package com.estiven.firebase_firestore

expect class QuerySnapshot {
    val documentChanges: MutableList<DocumentChange>
    val documents: MutableList<DocumentSnapshot>
    val isEmpty: Boolean
    val query: Query
    val metadata: SnapshotMetadata
    fun size(): Int
    fun getDocumentChanges(metadataChanges: MetadataChanges): MutableList<DocumentChange>
    inline fun <reified T : Any> toObjects(): List<T>
}