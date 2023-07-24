/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:02
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

actual class DocumentReference(val iOS: FIRDocumentReference) {
    actual val id
        get() = iOS.documentID
    actual val parent
        get() = CollectionReference(iOS.parent)
    actual val path
        get() = iOS.path
    actual val firestore
        get() = FirebaseFirestore(iOS.firestore)

    actual suspend fun delete() = await { iOS.deleteDocumentWithCompletion(it) }

    @Suppress("UNCHECKED_CAST")
    actual suspend fun set(data: Any) = iOS.setData(data as Map<Any?, *>)
    actual suspend fun update(data: MutableMap<String, Any>) =
        await { iOS.updateData(data.toMap(), it) }

    actual suspend fun get() =
        DocumentSnapshot(awaitResult { iOS.getDocumentWithCompletion(it) })

    actual suspend fun get(source: Source) =
        DocumentSnapshot(awaitResult { iOS.getDocumentWithSource(source, it) })

    actual fun collection(collectionPath: String) =
        CollectionReference(iOS.collectionWithPath(collectionPath))

    actual val snapshotListener
        get() = callbackFlow {
            val listener = iOS.addSnapshotListener { firDocumentSnapshot, nsError ->
                if (nsError != null) close(Exception(nsError.toString()))
                if (firDocumentSnapshot != null) trySend(DocumentSnapshot(firDocumentSnapshot))
            }
            awaitClose { listener.remove() }
        }
}