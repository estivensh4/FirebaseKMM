package com.estivensh4.firebase_firestore

expect class DocumentChange {
    val newIndex: Int
    val oldIndex: Int
    val document: QueryDocumentSnapshot
    val type: DocumentType
}