package com.estiven.firebase_firestore

actual class QuerySnapshot(val android: com.google.firebase.firestore.QuerySnapshot) {
    actual val documentChanges
        get() = android.documentChanges.map { DocumentChange(it) }.toMutableList()
    actual val documents
        get() = android.documents.map { DocumentSnapshot(it) }.toMutableList()
    actual val isEmpty
        get() = android.isEmpty
    actual val query
        get() = Query(android.query)
    actual val metadata
        get() = SnapshotMetadata(android.metadata)

    actual fun size() = android.size()
    actual fun getDocumentChanges(metadataChanges: MetadataChanges) =
        android.getDocumentChanges(metadataChanges).map { DocumentChange(it) }
            .toMutableList()

    actual inline fun <reified T : Any> toObjects() = android.toObjects(T::class.java)
}