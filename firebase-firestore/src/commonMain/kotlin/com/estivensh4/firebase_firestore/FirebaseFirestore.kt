/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:02
 *
 */

package com.estivensh4.firebase_firestore

import com.estivensh4.firebase_app.Firebase

expect val Firebase.firestore: FirebaseFirestore
expect enum class MetadataChanges
expect enum class DocumentType
expect enum class Direction
expect enum class Source

expect class FirebaseFirestore {
    val firestoreSettings: FirestoreSettings
    //val app: FirestoreApp
    fun collection(collectionPath: String): CollectionReference
    fun collectionGroup(collectionId: String): Query
    fun document(documentPath: String): DocumentReference
    fun batch(): WriteBatch
    fun setLoggingEnabled(loggingEnabled: Boolean)
    fun useEmulator(host: String, port: Int)
    suspend fun clearPersistence()
    suspend fun disableNetwork()
    suspend fun enableNetwork()
    suspend fun terminate()
    suspend fun waitForPendingWrites()
    suspend fun runTransaction(result: (Transaction) -> Unit): Any?
    suspend fun setIndexConfiguration(json: String)
}