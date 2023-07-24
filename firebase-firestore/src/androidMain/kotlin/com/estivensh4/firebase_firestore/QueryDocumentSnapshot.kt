package com.estivensh4.firebase_firestore

actual class QueryDocumentSnapshot(val android: com.google.firebase.firestore.QueryDocumentSnapshot) {
    actual inline fun <reified T : Any> toObject() = android.toObject(T::class.java)
}