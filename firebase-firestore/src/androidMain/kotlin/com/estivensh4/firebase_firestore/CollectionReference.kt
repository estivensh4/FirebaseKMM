/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:22
 *
 */

package com.estivensh4.firebase_firestore

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


actual class CollectionReference(val collectionReference: com.google.firebase.firestore.CollectionReference) :
    Query(collectionReference) {
    actual val id
        get() = collectionReference.id
    actual val parent: DocumentReference?
        get() = collectionReference.parent?.let { DocumentReference(it) }
    actual val path
        get() = collectionReference.path
    actual val firestore
        get() = FirebaseFirestore(collectionReference.firestore)

    actual fun document() = DocumentReference(collectionReference.document())
    actual fun document(documentPath: String) =
        DocumentReference(collectionReference.document(documentPath))

    actual suspend fun add(data: Any) = DocumentReference(collectionReference.add(data).await())

    actual fun snapshots(metadataChanges: MetadataChanges) = callbackFlow {
        val snapshotListener =
            collectionReference.addSnapshotListener(metadataChanges) { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(QuerySnapshot(value))
            }
        awaitClose { snapshotListener.remove() }
    }

    actual suspend fun get() = QuerySnapshot(collectionReference.get().await())
    override fun toString(): String = collectionReference.toString()
    override fun hashCode(): Int = collectionReference.hashCode()
    override fun equals(other: Any?): Boolean =
        other is CollectionReference && collectionReference == other.collectionReference
}