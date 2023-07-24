package com.estivensh4.firebase_firestore

expect class FirestoreApp {
    val name: String
    val isDataCollectionDefaultEnabled: Boolean
    val options: FirestoreOptions
}