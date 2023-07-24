/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

expect class QuerySnapshot {
    val documentChanges: MutableList<DocumentChange>
    val documents: MutableList<DocumentSnapshot>
    val isEmpty: Boolean
    val query: Query
    val metadata: SnapshotMetadata
    fun size(): Int
    fun getDocumentChanges(metadataChanges: MetadataChanges): MutableList<DocumentChange>
    inline fun <reified T : Any> toObjects(): List<T>
}