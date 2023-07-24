/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import kotlinx.coroutines.flow.Flow

expect class CollectionReference : Query {
    val id: String
    val parent: DocumentReference?
    val path: String
    val firestore: FirebaseFirestore
    fun snapshots(metadataChanges: MetadataChanges): Flow<QuerySnapshot>
    fun document(): DocumentReference
    fun document(documentPath: String): DocumentReference
    suspend fun add(data: Any): DocumentReference
    suspend fun get(): QuerySnapshot
}