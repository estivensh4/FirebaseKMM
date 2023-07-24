package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRCollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

actual class CollectionReference(private val iOS: FIRCollectionReference) : Query(iOS) {
    actual val id
        get() = iOS.collectionID
    actual val parent: DocumentReference?
        get() = iOS.parent?.let { DocumentReference(it) }
    actual val path
        get() = iOS.path
    actual val firestore
        get() = FirebaseFirestore(iOS.firestore)

    actual fun document() = DocumentReference(iOS.documentWithAutoID())
    actual fun document(documentPath: String) =
        DocumentReference(iOS.documentWithPath(documentPath))


    actual suspend fun get() =
        QuerySnapshot(awaitResult { iOS.getDocumentsWithCompletion(it) })

    @Suppress("UNCHECKED_CAST")
    actual suspend fun add(data: Any) =
        DocumentReference(await {
            iOS.addDocumentWithData(
                data as Map<Any?, *>,
                it
            )
        })

    actual fun snapshots(metadataChanges: MetadataChanges) = callbackFlow {
        val listener =
            iOS.addSnapshotListenerWithIncludeMetadataChanges(
                metadataChanges == MetadataChanges.INCLUDE
            ) { firQuerySnapshot, nsError ->
                if (nsError != null) close(Exception(nsError.toString()))
                if (firQuerySnapshot != null) trySend(QuerySnapshot(firQuerySnapshot))
            }
        awaitClose { listener.remove() }
    }
}