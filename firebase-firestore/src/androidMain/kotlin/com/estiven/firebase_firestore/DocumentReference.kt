package com.estiven.firebase_firestore

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

actual class DocumentReference(val android: NativeDocumentReference) {
    actual val id
        get() = android.id
    actual val parent
        get() = CollectionReference(android.parent)
    actual val path
        get() = android.path
    actual val firestore
        get() = FirebaseFirestore(android.firestore)

    actual suspend fun delete() = android.delete().await().run { }
    actual suspend fun set(data: Any) = android.set(data).await().run { }
    actual suspend fun update(data: MutableMap<String, Any>) =
        android.update(data).await().run { }

    actual suspend fun get() = DocumentSnapshot(android.get().await())
    actual suspend fun get(source: Source) = DocumentSnapshot(android.get(source).await())
    actual fun collection(collectionPath: String) =
        CollectionReference(android.collection(collectionPath))

    actual val snapshotListener
        get() = callbackFlow {
            val snapshotListener = android.addSnapshotListener { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(DocumentSnapshot(value))
            }
            awaitClose { snapshotListener.remove() }
        }
}