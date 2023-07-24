/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import kotlinx.coroutines.flow.Flow

expect class DocumentReference {
    val id: String
    val parent: CollectionReference
    val path: String
    val snapshotListener: Flow<DocumentSnapshot>
    val firestore: FirebaseFirestore
    suspend fun delete()
    suspend fun set(data: Any)
    suspend fun update(data: MutableMap<String, Any>)
    suspend fun get(): DocumentSnapshot
    suspend fun get(source: Source): DocumentSnapshot
    fun collection(collectionPath: String): CollectionReference
}