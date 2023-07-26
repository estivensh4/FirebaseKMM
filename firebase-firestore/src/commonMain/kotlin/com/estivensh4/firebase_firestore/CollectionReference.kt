/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerializationStrategy

expect class CollectionReference : Query {
    val id: String
    val parent: DocumentReference?
    val path: String
    val firestore: FirebaseFirestore
    fun snapshots(metadataChanges: MetadataChanges): Flow<QuerySnapshot>
    fun document(): DocumentReference
    fun document(documentPath: String): DocumentReference
    suspend inline fun <reified T> add(data: T, encodeDefaults: Boolean = true): DocumentReference
    @Deprecated("This will be replaced with add(strategy: SerializationStrategy<T>, data: T, encodeDefaults: Boolean = true)")
    suspend fun <T> add(data: T, strategy: SerializationStrategy<T>, encodeDefaults: Boolean = true): DocumentReference
    suspend fun <T> add(strategy: SerializationStrategy<T>, data: T, encodeDefaults: Boolean = true): DocumentReference
    suspend fun get(): QuerySnapshot
}