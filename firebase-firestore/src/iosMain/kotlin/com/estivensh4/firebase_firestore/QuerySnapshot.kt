/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentChange
import cocoapods.FirebaseFirestore.FIRDocumentSnapshot
import cocoapods.FirebaseFirestore.FIRQuerySnapshot

actual class QuerySnapshot(val iOS: FIRQuerySnapshot) {
    actual val documentChanges
        get() = iOS.documentChanges.map { DocumentChange(it as FIRDocumentChange) }
            .toMutableList()
    actual val documents
        get() = iOS.documents.map { DocumentSnapshot(it as FIRDocumentSnapshot) }
            .toMutableList()
    actual val isEmpty
        get() = iOS.isEmpty()
    actual val query
        get() = Query(iOS.query)
    actual val metadata
        get() = SnapshotMetadata(iOS.metadata)

    actual fun size() = iOS.count.toInt()

    actual fun getDocumentChanges(metadataChanges: MetadataChanges) =
        iOS.documentChangesWithIncludeMetadataChanges(metadataChanges == MetadataChanges.INCLUDE)
            .map {
                DocumentChange(it as FIRDocumentChange)
            }.toMutableList()

    @Suppress("UNCHECKED_CAST")
    actual inline fun <reified T : Any> toObjects() = iOS.documents as List<T>

}