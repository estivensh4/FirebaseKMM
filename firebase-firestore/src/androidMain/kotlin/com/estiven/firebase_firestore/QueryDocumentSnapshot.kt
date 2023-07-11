package com.estiven.firebase_firestore

actual class QueryDocumentSnapshot(val android: com.google.firebase.firestore.QueryDocumentSnapshot) {
    actual inline fun <reified T : Any> toObject() = mapToObject(android.data.toMap(), T::class)
}