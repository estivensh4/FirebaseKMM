package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase

expect val Firebase.firestore: FirebaseFirestore

expect class NativeFirestoreOptions
expect class NativeFirestore
expect class NativeFirestoreSettings
expect class NativeFirestoreApp
expect class NativeCollectionReference
expect class NativeDocumentReference
expect class NativeQuery
expect class NativeTransaction
expect class NativeQuerySnapshot
expect class NativeAggregateQuery
expect class NativeFieldPath
expect enum class Direction
expect enum class Source
expect class NativeDocumentSnapshot
expect class NativeFilter
expect class NativeWriteBatch
expect class NativeDocumentChange
expect class NativeSnapshotMetadata
expect enum class ServerTimestampBehavior
expect enum class MetadataChanges
expect enum class DocumentType

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