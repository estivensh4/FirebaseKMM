/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:22
 *
 */

package com.estivensh4.firebase_firestore

import com.estivensh4.firebase_common.encode
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.SerializationStrategy


actual class CollectionReference(val android: com.google.firebase.firestore.CollectionReference) :
    Query(android) {
    actual val id
        get() = android.id
    actual val parent: DocumentReference?
        get() = android.parent?.let { DocumentReference(it) }
    actual val path
        get() = android.path
    actual val firestore
        get() = FirebaseFirestore(android.firestore)

    actual fun document() = DocumentReference(android.document())
    actual fun document(documentPath: String) =
        DocumentReference(android.document(documentPath))

    actual suspend inline fun <reified T> add(data: T, encodeDefaults: Boolean) =
        DocumentReference(android.add(encode(data, encodeDefaults)!!).await())

    actual suspend fun <T> add(
        data: T,
        strategy: SerializationStrategy<T>,
        encodeDefaults: Boolean
    ) =
        DocumentReference(android.add(encode(strategy, data, encodeDefaults)!!).await())

    actual suspend fun <T> add(
        strategy: SerializationStrategy<T>,
        data: T,
        encodeDefaults: Boolean
    ) =
        DocumentReference(android.add(encode(strategy, data, encodeDefaults)!!).await())

    actual fun snapshots(metadataChanges: MetadataChanges) = callbackFlow {
        val snapshotListener =
            android.addSnapshotListener(metadataChanges) { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(QuerySnapshot(value))
            }
        awaitClose { snapshotListener.remove() }
    }

    actual suspend fun get() = QuerySnapshot(android.get().await())
    override fun toString(): String = android.toString()
    override fun hashCode(): Int = android.hashCode()
    override fun equals(other: Any?): Boolean =
        other is CollectionReference && android == other.android
}