package com.estiven.firebase_firestore

import cocoapods.FirebaseCore.FIRApp
import cocoapods.FirebaseCore.FIROptions
import cocoapods.FirebaseFirestore.*
import com.estiven.firebase_app.Firebase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import platform.Foundation.NSError

actual val Firebase.firestore
    get() = FirebaseFirestore(FIRFirestore.firestore())

actual typealias NativeFirestoreSettings = FIRFirestoreSettings
actual typealias NativeFirestore = FIRFirestore
actual typealias NativeFirestoreApp = FIRApp
actual typealias NativeFirestoreOptions = FIROptions
actual typealias NativeCollectionReference = FIRCollectionReference
actual typealias NativeQuerySnapshot = FIRQuerySnapshot
actual typealias NativeFieldPath = FIRFieldPath
actual typealias NativeFilter = FIRFilter
actual typealias NativeDocumentReference = FIRDocumentReference
actual typealias NativeWriteBatch = FIRWriteBatch
actual typealias NativeQuery = FIRQuery
actual typealias NativeDocumentChange = FIRDocumentChange
actual typealias NativeSnapshotMetadata = FIRSnapshotMetadata
actual typealias NativeTransaction = FIRTransaction
actual typealias ServerTimestampBehavior = FIRServerTimestampBehavior
actual typealias NativeDocumentSnapshot = FIRDocumentSnapshot
actual typealias Source = FIRFirestoreSource
actual typealias NativeAggregateQuery = FIRAggregateQuery

actual enum class MetadataChanges {
    EXCLUDE,
    INCLUDE
}

actual enum class Direction {
    ASCENDING,
    DESCENDING
}

actual class FirebaseFirestore(private val iOS: FIRFirestore) {
    actual val firestoreSettings
        get() = FirestoreSettings(iOS.settings)
    actual val app
        get() = FirestoreApp(iOS.app as FIRApp)

    actual fun collection(collectionPath: String) =
        CollectionReference(iOS.collectionWithPath(collectionPath))

    actual fun document(documentPath: String) =
        DocumentReference(iOS.documentWithPath(documentPath))

    actual fun batch() = WriteBatch(iOS.batch())
    actual suspend fun clearPersistence() = await { iOS.clearPersistenceWithCompletion(it) }
    actual suspend fun disableNetwork() = await { iOS.disableNetworkWithCompletion(it) }
    actual suspend fun enableNetwork() = await { iOS.enableNetworkWithCompletion(it) }
    actual suspend fun terminate() = await { iOS.terminateWithCompletion(it) }

    actual suspend fun setIndexConfiguration(json: String) =
        await { iOS.setIndexConfigurationFromJSON(json, it) }

    actual fun useEmulator(
        host: String,
        port: Int
    ) = iOS.useEmulatorWithHost(host, port.toLong())

    actual fun collectionGroup(collectionId: String) =
        Query(iOS.collectionGroupWithID(collectionId))

    actual suspend fun waitForPendingWrites() =
        await { iOS.waitForPendingWritesWithCompletion(it) }

    actual fun setLoggingEnabled(loggingEnabled: Boolean) =
        FIRFirestore.enableLogging(loggingEnabled)

    actual suspend fun runTransaction(
        result: (Transaction) -> Unit
    ) = awaitResult<Any?> {
        iOS.runTransactionWithBlock(
            updateBlock = { transaction, _ ->
                result(Transaction(transaction!!))
            },
            completion = it
        )
    }
}

actual class FirestoreSettings(private val iOS: NativeFirestoreSettings) {
    actual val host
        get() = iOS.host
    actual val isSslEnabled
        get() = iOS.sslEnabled
    actual val cacheSizeBytes
        get() = iOS.cacheSizeBytes
    actual val isPersistenceEnabled
        get() = iOS.persistenceEnabled
}

actual class FirestoreApp(private val iOS: NativeFirestoreApp) {
    actual val name
        get() = iOS.name
    actual val isDataCollectionDefaultEnabled
        get() = iOS.isDataCollectionDefaultEnabled()
    actual val options
        get() = FirestoreOptions(iOS.options)
}

actual class CollectionReference(
    private val iOS: FIRCollectionReference
) : Query(iOS) {
    actual val id
        get() = iOS.collectionID
    actual val parent: DocumentReference?
        get() = iOS.parent?.let { DocumentReference(it) }
    actual val path
        get() = iOS.path
    actual val firestore
        get() = FirebaseFirestore(iOS.firestore)

    actual fun document() = DocumentReference(iOS.documentWithAutoID())
    actual fun document(documentPath: String) =
        DocumentReference(iOS.documentWithPath(documentPath))


    actual suspend fun get() =
        QuerySnapshot(awaitResult { iOS.getDocumentsWithCompletion(it) })

    @Suppress("UNCHECKED_CAST")
    actual suspend fun add(data: Any) =
        DocumentReference(await {
            iOS.addDocumentWithData(
                data as Map<Any?, *>,
                it
            )
        })

    actual val snapshotListener
        get() = callbackFlow {
            val listener = iOS.addSnapshotListener { firQuerySnapshot, nsError ->
                if (nsError != null) close(Exception(nsError.toString()))
                if (firQuerySnapshot != null) trySend(QuerySnapshot(firQuerySnapshot))
            }
            awaitClose { listener.remove() }
        }

    actual fun snapshots(metadataChanges: MetadataChanges) = callbackFlow {
        val listener =
            iOS.addSnapshotListenerWithIncludeMetadataChanges(
                metadataChanges == MetadataChanges.INCLUDE
            ) { firQuerySnapshot, nsError ->
                if (nsError != null) close(Exception(nsError.toString()))
                if (firQuerySnapshot != null) trySend(QuerySnapshot(firQuerySnapshot))
            }
        awaitClose { listener.remove() }
    }
}

actual class DocumentReference(val iOS: NativeDocumentReference) {
    actual val id
        get() = iOS.documentID
    actual val parent
        get() = CollectionReference(iOS.parent)
    actual val path
        get() = iOS.path
    actual val firestore
        get() = FirebaseFirestore(iOS.firestore)

    actual suspend fun delete() = await { iOS.deleteDocumentWithCompletion(it) }

    @Suppress("UNCHECKED_CAST")
    actual suspend fun set(data: Any) = iOS.setData(data as Map<Any?, *>)
    actual suspend fun update(data: MutableMap<String, Any>) =
        await { iOS.updateData(data.toMap(), it) }

    actual suspend fun get() =
        DocumentSnapshot(awaitResult { iOS.getDocumentWithCompletion(it) })

    actual suspend fun get(source: Source) =
        DocumentSnapshot(awaitResult { iOS.getDocumentWithSource(source, it) })

    actual fun collection(collectionPath: String) =
        CollectionReference(iOS.collectionWithPath(collectionPath))

    actual val snapshotListener
        get() = callbackFlow {
            val listener = iOS.addSnapshotListener { firDocumentSnapshot, nsError ->
                if (nsError != null) close(Exception(nsError.toString()))
                if (firDocumentSnapshot != null) trySend(DocumentSnapshot(firDocumentSnapshot))
            }
            awaitClose { listener.remove() }
        }
}

actual class WriteBatch(private val iOS: FIRWriteBatch) {
    @Suppress("UNCHECKED_CAST")
    actual fun set(documentRef: DocumentReference, data: Any) =
        WriteBatch(iOS.setData(data as Map<Any?, *>, documentRef.iOS))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        WriteBatch(iOS.updateData(data.toMap(), documentRef.iOS))

    actual fun delete(documentRef: DocumentReference) =
        WriteBatch(iOS.deleteDocument(documentRef.iOS))

    actual suspend fun commit() = await { iOS.commitWithCompletion(it) }
}

actual open class Query(private val iOS: FIRQuery) {

    actual fun count() = AggregateQuery(iOS.count())
    actual fun endAt(vararg fieldValues: Any) =
        Query(iOS.queryEndingAtValues(fieldValues.toList()))

    actual fun endBefore(vararg fieldValues: Any) =
        Query(iOS.queryEndingBeforeValues(fieldValues.toList()))
    actual fun limit(limit: Long) = Query(iOS.queryLimitedTo(limit))
    actual fun limitToLast(limit: Long) = Query(iOS.queryLimitedToLast(limit))
    actual fun orderBy(field: String) = Query(iOS.queryOrderedByField(field))
    actual fun orderBy(field: FieldPath) = Query(iOS.queryOrderedByFieldPath(field.iOS))
    actual fun orderBy(field: String, direction: Direction) =
        Query(iOS.queryOrderedByField(field, direction == Direction.DESCENDING))

    actual fun orderBy(field: FieldPath, direction: Direction) =
        Query(iOS.queryOrderedByFieldPath(field.iOS, direction == Direction.DESCENDING))

    actual fun startAt(vararg fieldValues: Any) =
        Query(iOS.queryStartingAtValues(fieldValues.toList()))

    actual fun startAt(snapshot: DocumentSnapshot) =
        Query(iOS.queryStartingAtDocument(snapshot.iOS))

    actual fun startAfter(vararg fieldValues: Any) =
        Query(iOS.queryStartingAfterValues(fieldValues.toList()))

    actual fun startAfter(snapshot: DocumentSnapshot) =
        Query(iOS.queryStartingAtDocument(snapshot.iOS))

    actual fun where(filter: Filter) = Query(iOS.queryWhereFilter(filter.iOS))
    actual fun whereEqualTo(field: String, value: Any?) =
        Query(iOS.queryWhereField(field = field, isEqualTo = value!!))

    actual fun whereEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isEqualTo = value!!))

    actual fun whereNotEqualTo(field: String, value: Any?) =
        Query(iOS.queryWhereField(field = field, isNotEqualTo = value!!))

    actual fun whereNotEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isNotEqualTo = value!!))

    actual fun whereArrayContains(field: String, value: Any) =
        Query(iOS.queryWhereField(field = field, arrayContains = value))

    actual fun whereArrayContains(fieldPath: FieldPath, value: Any) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, arrayContains = value))

    actual fun whereGreaterThan(field: String, value: Any) =
        Query(iOS.queryWhereField(field = field, isGreaterThan = value))

    actual fun whereGreaterThan(fieldPath: FieldPath, value: Any) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isGreaterThan = value))

    actual fun whereLessThan(field: String, value: Any) =
        Query(iOS.queryWhereField(field = field, isLessThan = value))

    actual fun whereLessThan(fieldPath: FieldPath, value: Any) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isLessThan = value))

    actual fun whereIn(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, `in` = values))

    actual fun whereIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, `in` = values))

    actual fun whereArrayContainsAny(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, arrayContains = values))

    actual fun whereArrayContainsAny(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, arrayContains = values))

    actual fun whereGreaterThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, isGreaterThanOrEqualTo = values))

    actual fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isGreaterThanOrEqualTo = values))

    actual fun whereLessThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field, isLessThanOrEqualTo = values))

    actual fun whereLessThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isLessThanOrEqualTo = values))

    actual fun whereNotIn(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, notIn = values))

    actual fun whereNotIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, notIn = values))
}

actual class QuerySnapshot(private val iOS: FIRQuerySnapshot) {
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

}

actual class AggregateQuery(private val iOS: NativeAggregateQuery) {
    actual val query
        get() = iOS.query
}

actual class Transaction(private val iOS: NativeTransaction) {

}

actual class FirestoreOptions(private val firebaseOptions: NativeFirestoreOptions) {

}

actual class FieldPath(val iOS: NativeFieldPath) {
    override fun toString(): String = iOS.toString()
    override fun hashCode(): Int = iOS.hashCode()
    override fun equals(other: Any?): Boolean = other is FieldPath && iOS == other.iOS
}

actual class DocumentSnapshot(val iOS: NativeDocumentSnapshot) {
    actual fun get(field: String) = iOS.valueForField(field)
    actual fun get(fieldPath: FieldPath) = iOS.valueForField(fieldPath)
    actual fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior) =
        iOS.valueForField(field, serverTimestampBehavior)

    actual fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior) =
        iOS.valueForField(fieldPath, serverTimestampBehavior)
}

actual class DocumentChange(private val iOS: NativeDocumentChange) {

}

actual class SnapshotMetadata(private val iOS: NativeSnapshotMetadata) {

}

actual class Filter(val iOS: NativeFilter) {
    override fun toString(): String = iOS.toString()
    override fun hashCode(): Int = iOS.hashCode()
    override fun equals(other: Any?): Boolean = other is Filter && iOS == other.iOS
}

suspend inline fun <reified T> awaitResult(function: (callback: (T?, NSError?) -> Unit) -> Unit): T {
    val job = CompletableDeferred<T?>()
    function { result, error ->
        if (error == null) {
            job.complete(result)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    return job.await() as T
}

suspend inline fun <T> await(function: (callback: (NSError?) -> Unit) -> T): T {
    val job = CompletableDeferred<Unit>()
    val result = function { error ->
        if (error == null) {
            job.complete(Unit)
        } else {
            job.completeExceptionally(Exception(""))
        }
    }
    job.await()
    return result
}