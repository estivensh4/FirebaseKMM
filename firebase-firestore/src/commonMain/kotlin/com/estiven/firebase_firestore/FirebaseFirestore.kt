package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase
import kotlinx.coroutines.flow.Flow

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

expect class FirebaseFirestore {
    val firestoreSettings: FirestoreSettings
    val app: FirestoreApp
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

expect class FirestoreSettings {
    val host: String
    val isSslEnabled: Boolean
    val cacheSizeBytes: Long
    val isPersistenceEnabled: Boolean
}

expect class FirestoreApp {
    val name: String
    val isDataCollectionDefaultEnabled: Boolean
    val options: FirestoreOptions
}

expect class FirestoreOptions {
    val apiKey: String
    val applicationId: String
    val databaseUrl: String?
    val gaTrackingId: String?
    val gcmSenderId: String?
    val storageBucket: String?
    val projectId: String?
}

expect class CollectionReference {
    val id: String
    val parent: DocumentReference?
    val path: String
    val snapshotListener: Flow<QuerySnapshot>
    val firestore: FirebaseFirestore
    fun snapshots(metadataChanges: MetadataChanges): Flow<QuerySnapshot>
    fun document(): DocumentReference
    fun document(documentPath: String): DocumentReference
    suspend fun add(data: Any): DocumentReference
    suspend fun get(): QuerySnapshot
}

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

expect class WriteBatch {
    fun set(documentRef: DocumentReference, data: Any): WriteBatch
    fun update(
        documentRef: DocumentReference,
        data: MutableMap<String, Any>
    ): WriteBatch

    fun delete(
        documentRef: DocumentReference
    ): WriteBatch

    suspend fun commit()
}

expect open class Query {
    fun count(): AggregateQuery
    fun endAt(vararg fieldValues: Any): Query
    fun endBefore(vararg fieldValues: Any): Query
    fun limit(limit: Long): Query
    fun limitToLast(limit: Long): Query
    fun orderBy(field: String): Query
    fun orderBy(field: FieldPath): Query
    fun orderBy(field: String, direction: Direction): Query
    fun orderBy(field: FieldPath, direction: Direction): Query
    fun startAt(vararg fieldValues: Any): Query
    fun startAt(snapshot: DocumentSnapshot): Query
    fun startAfter(vararg fieldValues: Any): Query
    fun startAfter(snapshot: DocumentSnapshot): Query
    fun where(filter: Filter): Query
    fun whereEqualTo(field: String, value: Any?): Query
    fun whereEqualTo(fieldPath: FieldPath, value: Any?): Query
    fun whereNotEqualTo(field: String, value: Any?): Query
    fun whereNotEqualTo(fieldPath: FieldPath, value: Any?): Query
    fun whereArrayContains(field: String, value: Any): Query
    fun whereArrayContains(fieldPath: FieldPath, value: Any): Query
    fun whereGreaterThan(field: String, value: Any): Query
    fun whereGreaterThan(fieldPath: FieldPath, value: Any): Query
    fun whereLessThan(field: String, value: Any): Query
    fun whereLessThan(fieldPath: FieldPath, value: Any): Query
    fun whereIn(field: String, values: MutableList<Any>): Query
    fun whereIn(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereArrayContainsAny(field: String, values: MutableList<Any>): Query
    fun whereArrayContainsAny(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereGreaterThanOrEqualTo(field: String, values: MutableList<Any>): Query
    fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereLessThanOrEqualTo(field: String, values: MutableList<Any>): Query
    fun whereLessThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereNotIn(field: String, values: MutableList<Any>): Query
    fun whereNotIn(fieldPath: FieldPath, values: MutableList<Any>): Query
}

expect class Transaction {
    fun set(documentRef: DocumentReference, data: Any): Transaction
    fun update(
        documentRef: DocumentReference,
        data: MutableMap<String, Any>
    ): Transaction

    fun update(
        documentRef: DocumentReference,
        field: String,
        value: Any?,
        vararg moreFieldsAndValues: Any
    ): Transaction

    fun update(
        documentRef: DocumentReference,
        fieldPath: FieldPath,
        value: Any?,
        vararg moreFieldsAndValues: Any
    ): Transaction

    suspend fun delete(documentRef: DocumentReference)
    suspend fun get(documentRef: DocumentReference): DocumentSnapshot
}

expect class QuerySnapshot {
    val documentChanges: MutableList<DocumentChange>
    val documents: MutableList<DocumentSnapshot>
    val isEmpty: Boolean
    val query: Query
    val metadata: SnapshotMetadata
    fun size(): Int
    fun getDocumentChanges(metadataChanges: MetadataChanges): MutableList<DocumentChange>
}

expect class AggregateQuery {
    val query: NativeQuery
}

expect class FieldPath {

}

expect class DocumentSnapshot {
    fun get(field: String): Any?
    fun get(fieldPath: FieldPath): Any?
    fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior): Any?
    fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior): Any?
}

expect class Filter {

}

expect class DocumentChange {

}

expect class SnapshotMetadata {

}
