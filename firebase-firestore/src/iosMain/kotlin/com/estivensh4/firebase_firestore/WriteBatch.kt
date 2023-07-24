package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRWriteBatch

actual class WriteBatch(private val iOS: FIRWriteBatch) {
    @Suppress("UNCHECKED_CAST")
    actual fun set(documentRef: DocumentReference, data: Any) =
        WriteBatch(iOS.setData(data as Map<Any?, *>, documentRef.iOS))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        WriteBatch(iOS.updateData(data.toMap(), documentRef.iOS))

    actual fun delete(documentRef: DocumentReference) =
        WriteBatch(iOS.deleteDocument(documentRef.iOS))

    actual suspend fun commit() = await { iOS.commitWithCompletion(it) }
}