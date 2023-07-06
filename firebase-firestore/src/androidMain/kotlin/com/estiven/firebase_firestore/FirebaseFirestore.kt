package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import org.jetbrains.annotations.Contract
import com.google.firebase.firestore.FirebaseFirestore as firebaseFirestore

actual val Firebase.firestore
    get() = FirebaseFirestore(firebaseFirestore.getInstance())

actual class FirebaseFirestore(private val android: firebaseFirestore) {

    actual val firestoreSettings
        get() = FirestoreSettings(android.firestoreSettings)
    actual val app
        get() = FirestoreApp(android.app)

    actual fun collection(collectionPath: String) =
        CollectionReference(android.collection(collectionPath))

    actual fun document(documentPath: String) =
        DocumentReference(android.document(documentPath))

    actual fun batch() = WriteBatch(android.batch())
    actual suspend fun clearPersistence() = android.clearPersistence().await().run {}
    actual suspend fun disableNetwork() = android.disableNetwork().await().run {}
    actual suspend fun enableNetwork() = android.enableNetwork().await().run {}
    actual suspend fun terminate() = android.terminate().await().run {}
    actual suspend fun setIndexConfiguration(json: String) =
        android.setIndexConfiguration(json).await().run {}

    actual fun useEmulator(
        host: String,
        port: Int
    ) = android.useEmulator(host, port)

    actual fun collectionGroup(collectionId: String) =
        Query(android.collectionGroup(collectionId))

    actual suspend fun runTransaction(
        result: (Transaction) -> Unit
    ): Any? = android.runTransaction { result(Transaction(it)) }.await().run {}

    actual suspend fun waitForPendingWrites() = android.waitForPendingWrites().await().run { }
    actual fun setLoggingEnabled(loggingEnabled: Boolean) =
        com.google.firebase.firestore.FirebaseFirestore.setLoggingEnabled(loggingEnabled)
}

actual typealias NativeFirestore = firebaseFirestore
actual typealias NativeFirestoreSettings = FirebaseFirestoreSettings
actual typealias NativeFirestoreApp = com.google.firebase.FirebaseApp
actual typealias NativeFirestoreOptions = FirebaseOptions
actual typealias NativeCollectionReference = com.google.firebase.firestore.CollectionReference
actual typealias NativeQuerySnapshot = com.google.firebase.firestore.QuerySnapshot
actual typealias Direction = com.google.firebase.firestore.Query.Direction
actual typealias NativeFieldPath = com.google.firebase.firestore.FieldPath
actual typealias Source = com.google.firebase.firestore.Source
actual typealias NativeFilter = com.google.firebase.firestore.Filter
actual typealias NativeDocumentReference = com.google.firebase.firestore.DocumentReference
actual typealias NativeWriteBatch = com.google.firebase.firestore.WriteBatch
actual typealias NativeQuery = com.google.firebase.firestore.Query
actual typealias NativeDocumentChange = com.google.firebase.firestore.DocumentChange
actual typealias NativeSnapshotMetadata = com.google.firebase.firestore.SnapshotMetadata
actual typealias NativeTransaction = com.google.firebase.firestore.Transaction
actual typealias ServerTimestampBehavior = com.google.firebase.firestore.DocumentSnapshot.ServerTimestampBehavior
actual typealias NativeDocumentSnapshot = com.google.firebase.firestore.DocumentSnapshot
actual typealias NativeAggregateQuery = com.google.firebase.firestore.AggregateQuery
actual typealias MetadataChanges = com.google.firebase.firestore.MetadataChanges

actual class FirestoreSettings(val android: FirebaseFirestoreSettings) {
    actual val host
        get() = android.host
    actual val isSslEnabled
        get() = android.isSslEnabled

    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val cacheSizeBytes
        get() = android.cacheSizeBytes

    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val isPersistenceEnabled
        get() = android.isPersistenceEnabled
}

actual class FirestoreApp(val android: com.google.firebase.FirebaseApp) {
    actual val name
        get() = android.name
    actual val isDataCollectionDefaultEnabled
        get() = android.isDataCollectionDefaultEnabled
    actual val options
        get() = FirestoreOptions(android.options)
}

actual class FirestoreOptions(val android: FirebaseOptions) {
    actual val apiKey
        get() = android.apiKey
    actual val applicationId
        get() = android.applicationId
    actual val databaseUrl
        get() = android.databaseUrl
    actual val gaTrackingId
        get() = android.gaTrackingId
    actual val gcmSenderId
        get() = android.gcmSenderId
    actual val storageBucket
        get() = android.storageBucket
    actual val projectId
        get() = android.projectId
}

actual class CollectionReference(val collectionReference: NativeCollectionReference) :
    Query(collectionReference) {
    actual val id
        get() = collectionReference.id
    actual val parent: DocumentReference?
        get() = collectionReference.parent?.let { DocumentReference(it) }
    actual val path
        get() = collectionReference.path
    actual val firestore
        get() = FirebaseFirestore(collectionReference.firestore)

    actual fun document() = DocumentReference(collectionReference.document())
    actual fun document(documentPath: String) =
        DocumentReference(collectionReference.document(documentPath))

    actual suspend fun add(data: Any) = DocumentReference(collectionReference.add(data).await())
    actual val snapshotListener
        get() = callbackFlow {
            val snapshotListener = collectionReference.addSnapshotListener { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(QuerySnapshot(value))
            }
            awaitClose { snapshotListener.remove() }
        }

    actual fun snapshots(metadataChanges: MetadataChanges) = callbackFlow {
        val snapshotListener =
            collectionReference.addSnapshotListener(metadataChanges) { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(QuerySnapshot(value))
            }
        awaitClose { snapshotListener.remove() }
    }

    actual suspend fun get() = QuerySnapshot(collectionReference.get().await())
    override fun toString(): String = collectionReference.toString()
    override fun hashCode(): Int = collectionReference.hashCode()
    override fun equals(other: Any?): Boolean =
        other is CollectionReference && collectionReference == other.collectionReference
}

actual class DocumentReference(val android: com.google.firebase.firestore.DocumentReference) {
    actual val id
        get() = android.id
    actual val parent
        get() = CollectionReference(android.parent)
    actual val path
        get() = android.path
    actual val firestore
        get() = FirebaseFirestore(android.firestore)

    actual suspend fun delete() = android.delete().await().run { }
    actual suspend fun set(data: Any) = android.set(data).await().run { }
    actual suspend fun update(data: MutableMap<String, Any>) =
        android.update(data).await().run { }
    actual suspend fun get() = DocumentSnapshot(android.get().await())
    actual suspend fun get(source: Source) = DocumentSnapshot(android.get(source).await())
    actual fun collection(collectionPath: String) =
        CollectionReference(android.collection(collectionPath))

    actual val snapshotListener
        get() = callbackFlow {
            val snapshotListener = android.addSnapshotListener { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(DocumentSnapshot(value))
            }
            awaitClose { snapshotListener.remove() }
        }
}

actual class WriteBatch(val android: com.google.firebase.firestore.WriteBatch) {
    actual fun set(documentRef: DocumentReference, data: Any) =
        WriteBatch(android.set(documentRef.android, data))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        WriteBatch(android.update(documentRef.android, data))

    actual fun delete(documentRef: DocumentReference) =
        WriteBatch(android.delete(documentRef.android))

    actual suspend fun commit() = android.commit().await().run { }
}

actual open class Query(val android: com.google.firebase.firestore.Query) {

    actual fun count() = AggregateQuery(android.count())
    actual fun endAt(vararg fieldValues: Any) = Query(android.endAt(fieldValues))
    actual fun endBefore(vararg fieldValues: Any) =
        Query(android.endBefore(fieldValues))

    actual fun limit(limit: Long) = Query(android.limit(limit))
    actual fun limitToLast(limit: Long) = Query(android.limitToLast(limit))
    actual fun orderBy(field: String) = Query(android.orderBy(field))
    actual fun orderBy(field: FieldPath) = Query(android.orderBy(field.android))
    actual fun orderBy(field: String, direction: Direction) =
        Query(android.orderBy(field, direction))

    actual fun orderBy(field: FieldPath, direction: Direction) =
        Query(android.orderBy(field.android, direction))

    actual fun startAt(vararg fieldValues: Any) = Query(android.startAt(fieldValues))
    actual fun startAt(snapshot: DocumentSnapshot) =
        Query(android.startAt(snapshot.android))

    actual fun startAfter(vararg fieldValues: Any) =
        Query(android.startAfter(fieldValues))

    actual fun startAfter(snapshot: DocumentSnapshot) =
        Query(android.startAfter(snapshot.android))

    actual fun where(filter: Filter) = Query(android.where(filter.android))
    actual fun whereEqualTo(field: String, value: Any?) =
        Query(android.whereEqualTo(field, value))

    actual fun whereEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(android.whereEqualTo(fieldPath.android, value))

    actual fun whereNotEqualTo(field: String, value: Any?) =
        Query(android.whereNotEqualTo(field, value))

    actual fun whereNotEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(android.whereNotEqualTo(fieldPath.android, value))

    actual fun whereArrayContains(field: String, value: Any) =
        Query(android.whereArrayContains(field, value))

    actual fun whereArrayContains(fieldPath: FieldPath, value: Any) =
        Query(android.whereEqualTo(fieldPath.android, value))

    actual fun whereGreaterThan(field: String, value: Any) =
        Query(android.whereGreaterThan(field, value))

    actual fun whereGreaterThan(fieldPath: FieldPath, value: Any) =
        Query(android.whereGreaterThan(fieldPath.android, value))

    actual fun whereLessThan(field: String, value: Any) =
        Query(android.whereLessThan(field, value))

    actual fun whereLessThan(fieldPath: FieldPath, value: Any) =
        Query(android.whereLessThan(fieldPath.android, value))

    actual fun whereIn(field: String, values: MutableList<Any>) =
        Query(android.whereIn(field, values))

    actual fun whereIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereIn(fieldPath.android, values))

    actual fun whereArrayContainsAny(field: String, values: MutableList<Any>) =
        Query(android.whereArrayContainsAny(field, values))

    actual fun whereArrayContainsAny(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereArrayContainsAny(fieldPath.android, values))

    actual fun whereGreaterThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(android.whereGreaterThanOrEqualTo(field, values))

    actual fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereGreaterThanOrEqualTo(fieldPath.android, values))

    actual fun whereLessThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(android.whereLessThanOrEqualTo(field, values))

    actual fun whereLessThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereLessThanOrEqualTo(fieldPath.android, values))

    actual fun whereNotIn(field: String, values: MutableList<Any>) =
        Query(android.whereNotIn(field, values))

    actual fun whereNotIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereNotIn(fieldPath.android, values))
}

actual class Transaction(val android: NativeTransaction) {
    actual fun set(documentRef: DocumentReference, data: Any) =
        Transaction(android.set(documentRef.android, data))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        Transaction(android.update(documentRef.android, data))

    actual fun update(
        documentRef: DocumentReference,
        field: String,
        value: Any?,
        vararg moreFieldsAndValues: Any
    ) =
        Transaction(android.update(documentRef.android, field, value, moreFieldsAndValues))

    actual fun update(
        documentRef: DocumentReference,
        fieldPath: FieldPath,
        value: Any?,
        vararg moreFieldsAndValues: Any
    ) =
        Transaction(
            android.update(
                documentRef.android,
                fieldPath.android,
                value,
                moreFieldsAndValues
            )
        )

    actual suspend fun delete(documentRef: DocumentReference) = documentRef.delete()
    actual suspend fun get(documentRef: DocumentReference) =
        DocumentSnapshot(documentRef.get().android)
}


actual class QuerySnapshot(val android: com.google.firebase.firestore.QuerySnapshot) {
    actual val documentChanges
        get() = android.documentChanges.map { DocumentChange(it) }.toMutableList()
    actual val documents
        get() = android.documents.map { DocumentSnapshot(it) }.toMutableList()
    actual val isEmpty
        get() = android.isEmpty
    actual val query
        get() = Query(android.query)
    actual val metadata
        get() = SnapshotMetadata(android.metadata)

    actual fun size() = android.size()
    actual fun getDocumentChanges(metadataChanges: MetadataChanges) =
        android.getDocumentChanges(metadataChanges).map { DocumentChange(it) }
            .toMutableList()
}

actual class AggregateQuery(val aggregateQuery: NativeAggregateQuery) {
    actual val query
        get() = aggregateQuery.query
}

actual class FieldPath(val android: com.google.firebase.firestore.FieldPath) {

    override fun toString(): String = android.toString()
    override fun hashCode(): Int = android.hashCode()
    override fun equals(other: Any?): Boolean = other is FieldPath && android == other.android
}


actual class DocumentSnapshot(val android: NativeDocumentSnapshot) {
    actual fun get(field: String) = android.get(field)
    actual fun get(fieldPath: FieldPath) = android.get(fieldPath.android)
    actual fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior) =
        android.get(field, serverTimestampBehavior)

    actual fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior) =
        android.get(fieldPath.android, serverTimestampBehavior)
}

actual class Filter(val android: NativeFilter) {
    override fun toString(): String = android.toString()
    override fun hashCode(): Int = android.hashCode()
    override fun equals(other: Any?): Boolean = other is Filter && android == other.android
}

actual class DocumentChange(val documentChange: NativeDocumentChange) {

}

actual class SnapshotMetadata(val documentChange: NativeSnapshotMetadata) {

}
