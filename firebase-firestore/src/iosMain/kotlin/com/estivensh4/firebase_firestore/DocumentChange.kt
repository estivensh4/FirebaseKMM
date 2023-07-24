package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentChange

actual class DocumentChange(private val iOS: FIRDocumentChange) {
    actual val newIndex get() = iOS.newIndex.toInt()
    actual val oldIndex get() = iOS.oldIndex.toInt()
    actual val document get() = QueryDocumentSnapshot(iOS.document)
    actual val type get() = iOS.type.toIos()
}