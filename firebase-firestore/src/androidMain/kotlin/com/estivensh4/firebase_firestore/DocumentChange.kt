package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.DocumentChange

actual class DocumentChange(val android: DocumentChange) {
    actual val newIndex get() = android.newIndex
    actual val oldIndex get() = android.oldIndex
    actual val document get() = QueryDocumentSnapshot(android.document)
    actual val type get() = android.type.toAndroid()
}