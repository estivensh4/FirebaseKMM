package com.estiven.firebase_firestore

actual class DocumentChange(val android: NativeDocumentChange) {
    actual val newIndex get() = android.newIndex
    actual val oldIndex get() = android.oldIndex
    actual val document get() = QueryDocumentSnapshot(android.document)
    actual val type get() = android.type.toAndroid()
}