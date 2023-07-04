package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase
import kotlinx.coroutines.flow.Flow


expect val Firebase.firestore: FirebaseFirestore

expect interface NativeCacheSettings
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

expect class FirebaseFirestore internal constructor(private val nativeValue: NativeFirestore) {
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
    suspend fun runTransaction(result: (Transaction) -> Unit)
    suspend fun runBatch(result: (WriteBatch) -> Unit)
    suspend fun setIndexConfiguration(json: String)
    suspend fun getNamedQuery(name: String): Query
}

expect class FirestoreSettings internal constructor(val firestoreSettings: NativeFirestoreSettings) {
    val host: String
    val isSslEnabled: Boolean
    val cacheSettings: CacheSettings
    val cacheSizeBytes: Long
    val isPersistenceEnabled: Boolean
}

expect class CacheSettings internal constructor(val cacheSettings: NativeCacheSettings) {

}

expect class FirestoreApp internal constructor(val androidApp: NativeFirestoreApp) {
    val name: String
    val isDataCollectionDefaultEnabled: Boolean
    val isDefaultApp: Boolean
    val persistenceKey: String?
    val options: FirestoreOptions
    val applicationContext: Any
}

expect class FirestoreOptions internal constructor(val firebaseOptions: NativeFirestoreOptions) {
    val apiKey: String
    val applicationId: String
    val databaseUrl: String?
    val gaTrackingId: String?
    val gcmSenderId: String?
    val storageBucket: String?
    val projectId: String?
}

expect class CollectionReference internal constructor(val collectionReference: NativeCollectionReference) {
    val id: String
    val parent: DocumentReference
    val path: String
    val snapshotListener: Flow<QuerySnapshot>
    fun document(): DocumentReference
    fun document(documentPath: String): DocumentReference
    fun limit(limit: Long): Query
    fun count(): AggregateQuery
    fun endAt(vararg fieldValues: Any): Query
    fun endBefore(vararg fieldValues: Any): Query
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

    suspend fun add(data: Any): DocumentReference
    suspend fun get(): QuerySnapshot
    suspend fun get(source: Source): QuerySnapshot
}


expect class DocumentReference internal constructor(val documentReference: NativeDocumentReference) {

}

expect class WriteBatch {

}

expect class Query internal constructor(val query: NativeQuery) {

}

expect class Transaction internal constructor(val transaction: NativeTransaction) {

}

expect class QuerySnapshot internal constructor(val querySnapshot: NativeQuerySnapshot) {

}

expect class AggregateQuery internal constructor(val aggregateQuery: NativeAggregateQuery) {

}

expect class FieldPath internal constructor(val android: NativeFieldPath) {

}

expect class DocumentSnapshot internal constructor(val android: NativeDocumentSnapshot) {

}

expect class Filter internal constructor(val android: NativeFilter) {

}

