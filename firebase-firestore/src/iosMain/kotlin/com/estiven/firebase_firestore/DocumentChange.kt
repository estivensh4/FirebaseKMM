package com.estiven.firebase_firestore

actual class DocumentChange(private val iOS: NativeDocumentChange) {
    actual val newIndex get() = iOS.newIndex.toInt()
    actual val oldIndex get() = iOS.oldIndex.toInt()
    actual val document get() = QueryDocumentSnapshot(iOS.document)
    actual val type get() = iOS.type.toIos()
}