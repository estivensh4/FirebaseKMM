/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRCollectionReference
import com.estivensh4.firebase_common.await
import com.estivensh4.firebase_common.awaitResult
import com.estivensh4.firebase_common.encode
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.SerializationStrategy

actual class CollectionReference(override val iOS: FIRCollectionReference) : Query(iOS) {
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

    /**
     * Get.
     *
     * @return
     */
    actual suspend fun get() =
        QuerySnapshot(awaitResult { iOS.getDocumentsWithCompletion(it) })

    /**
     * Add.
     *
     * @param T T
     * @param data Data
     * @param encodeDefaults Encode defaults
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    actual suspend inline fun <reified T> add(data: T, encodeDefaults: Boolean) =
        DocumentReference(await {
            iOS.addDocumentWithData(encode(data, encodeDefaults) as Map<Any?, *>, it)
        })

    /**
     * Add.
     *
     * @param T T
     * @param data Data
     * @param strategy Strategy
     * @param encodeDefaults Encode defaults
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    actual suspend fun <T> add(
        data: T,
        strategy: SerializationStrategy<T>,
        encodeDefaults: Boolean
    ) =
        DocumentReference(await {
            iOS.addDocumentWithData(encode(strategy, data, encodeDefaults) as Map<Any?, *>, it)
        })

    /**
     * Add.
     *
     * @param T T
     * @param strategy Strategy
     * @param data Data
     * @param encodeDefaults Encode defaults
     * @return
     */
    @Suppress("UNCHECKED_CAST")
    actual suspend fun <T> add(
        strategy: SerializationStrategy<T>,
        data: T,
        encodeDefaults: Boolean
    ) =
        DocumentReference(await {
            iOS.addDocumentWithData(encode(strategy, data, encodeDefaults) as Map<Any?, *>, it)
        })

    /**
     * Snapshots.
     *
     * @param metadataChanges Metadata changes
     * @return
     */
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