package com.estiven.firebase_firestore

actual class Transaction(val android: NativeTransaction) {
    actual fun set(documentRef: DocumentReference, data: Any) =
        Transaction(android.set(documentRef.android, data))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        Transaction(android.update(documentRef.android, data))

    actual suspend fun delete(documentRef: DocumentReference) = documentRef.delete()
    actual suspend fun get(documentRef: DocumentReference) =
        DocumentSnapshot(documentRef.get().android)
}
