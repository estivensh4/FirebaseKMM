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

actual typealias  NativeFirestore = firebaseFirestore

actual class FirebaseFirestore actual constructor(private actual val nativeValue: firebaseFirestore) {

    actual val firestoreSettings
        get() = FirestoreSettings(nativeValue.firestoreSettings)
    actual val app
        get() = FirestoreApp(nativeValue.app)

    actual fun collection(collectionPath: String) =
        CollectionReference(nativeValue.collection(collectionPath))

    actual fun document(documentPath: String) =
        DocumentReference(nativeValue.document(documentPath))

    actual fun batch() = WriteBatch(nativeValue.batch())
    actual suspend fun clearPersistence() = nativeValue.clearPersistence().await().run {}
    actual suspend fun disableNetwork() = nativeValue.disableNetwork().await().run {}
    actual suspend fun enableNetwork() = nativeValue.enableNetwork().await().run {}
    actual suspend fun terminate() = nativeValue.terminate().await().run {}
    actual suspend fun setIndexConfiguration(json: String) =
        nativeValue.setIndexConfiguration(json).await().run {}

    actual fun useEmulator(
        host: String,
        port: Int
    ) = nativeValue.useEmulator(host, port)

    actual fun collectionGroup(collectionId: String) =
        Query(nativeValue.collectionGroup(collectionId))

    actual suspend fun runTransaction(
        result: (Transaction) -> Unit
    ) = nativeValue.runTransaction { result(Transaction(it)) }.await().run {}

    actual suspend fun runBatch(
        result: (WriteBatch) -> Unit
    ) = nativeValue.runBatch { result(WriteBatch(it)) }.await().run {}

    actual suspend fun waitForPendingWrites() = nativeValue.waitForPendingWrites().await().run { }
    actual fun setLoggingEnabled(loggingEnabled: Boolean) =
        com.google.firebase.firestore.FirebaseFirestore.setLoggingEnabled(loggingEnabled)

    actual suspend fun getNamedQuery(name: String) = Query(nativeValue.getNamedQuery(name).await())

}

actual typealias NativeFirestoreSettings = FirebaseFirestoreSettings

actual typealias NativeCacheSettings = com.google.firebase.firestore.LocalCacheSettings

actual typealias NativeFirestoreApp = com.google.firebase.FirebaseApp

actual typealias NativeFirestoreOptions = FirebaseOptions

actual typealias NativeCollectionReference = com.google.firebase.firestore.CollectionReference
actual typealias NativeQuerySnapshot = com.google.firebase.firestore.QuerySnapshot
actual typealias Direction = com.google.firebase.firestore.Query.Direction
actual typealias NativeFieldPath = com.google.firebase.firestore.FieldPath
actual typealias Source = com.google.firebase.firestore.Source
actual typealias NativeFilter = com.google.firebase.firestore.Filter

actual class FirestoreSettings actual constructor(actual val firestoreSettings: FirebaseFirestoreSettings) {
    actual val host
        get() = firestoreSettings.host
    actual val isSslEnabled
        get() = firestoreSettings.isSslEnabled
    actual val cacheSettings
        get() = CacheSettings(firestoreSettings.cacheSettings!!)

    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val cacheSizeBytes
        get() = firestoreSettings.cacheSizeBytes

    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val isPersistenceEnabled
        get() = firestoreSettings.isPersistenceEnabled
}

actual class CacheSettings actual constructor(actual val cacheSettings: com.google.firebase.firestore.LocalCacheSettings) {

}

actual class FirestoreApp actual constructor(actual val androidApp: com.google.firebase.FirebaseApp) {
    actual val name
        get() = androidApp.name
    actual val isDataCollectionDefaultEnabled
        get() = androidApp.isDataCollectionDefaultEnabled
    actual val isDefaultApp
        get() = androidApp.isDefaultApp
    actual val persistenceKey: String?
        get() = androidApp.persistenceKey
    actual val options
        get() = FirestoreOptions(androidApp.options)
    actual val applicationContext: Any
        get() = androidApp.applicationContext
}

actual class FirestoreOptions actual constructor(actual val firebaseOptions: FirebaseOptions) {
    actual val apiKey
        get() = firebaseOptions.apiKey
    actual val applicationId
        get() = firebaseOptions.applicationId
    actual val databaseUrl
        get() = firebaseOptions.databaseUrl
    actual val gaTrackingId
        get() = firebaseOptions.gaTrackingId
    actual val gcmSenderId
        get() = firebaseOptions.gcmSenderId
    actual val storageBucket
        get() = firebaseOptions.storageBucket
    actual val projectId
        get() = firebaseOptions.projectId
}

actual class CollectionReference actual constructor(actual val collectionReference: com.google.firebase.firestore.CollectionReference) {
    actual val id
        get() = collectionReference.id
    actual val parent
        get() = DocumentReference(collectionReference.parent)
    actual val path
        get() = collectionReference.path

    actual fun document() = DocumentReference(collectionReference.document())
    actual fun document(documentPath: String) =
        DocumentReference(collectionReference.document(documentPath))

    actual fun limit(limit: Long) = Query(collectionReference.limit(limit))
    actual suspend fun add(data: Any) = DocumentReference(collectionReference.add(data).await())
    actual val snapshotListener
        get() = callbackFlow {
            val snapshotListener = collectionReference.addSnapshotListener { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(QuerySnapshot(value))
            }
            awaitClose { snapshotListener.remove() }
        }

    actual suspend fun get() = QuerySnapshot(collectionReference.get().await())
    actual suspend fun get(source: Source) = QuerySnapshot(collectionReference.get(source).await())
    actual fun count() = AggregateQuery(collectionReference.count())
    actual fun endAt(vararg fieldValues: Any) = Query(collectionReference.endAt(fieldValues))
    actual fun endBefore(vararg fieldValues: Any) =
        Query(collectionReference.endBefore(fieldValues))

    actual fun limitToLast(limit: Long) = Query(collectionReference.limitToLast(limit))
    actual fun orderBy(field: String) = Query(collectionReference.orderBy(field))
    actual fun orderBy(field: FieldPath) = Query(collectionReference.orderBy(field.android))
    actual fun orderBy(field: String, direction: Direction) =
        Query(collectionReference.orderBy(field, direction))

    actual fun orderBy(field: FieldPath, direction: Direction) =
        Query(collectionReference.orderBy(field.android, direction))

    actual fun startAt(vararg fieldValues: Any) = Query(collectionReference.startAt(fieldValues))
    actual fun startAt(snapshot: DocumentSnapshot) =
        Query(collectionReference.startAt(snapshot.android))

    actual fun startAfter(vararg fieldValues: Any) =
        Query(collectionReference.startAfter(fieldValues))

    actual fun startAfter(snapshot: DocumentSnapshot) =
        Query(collectionReference.startAfter(snapshot.android))

    actual fun where(filter: Filter) = Query(collectionReference.where(filter.android))
    actual fun whereEqualTo(field: String, value: Any?) =
        Query(collectionReference.whereEqualTo(field, value))

    actual fun whereEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(collectionReference.whereEqualTo(fieldPath.android, value))
    actual fun whereNotEqualTo(field: String, value: Any?) =
        Query(collectionReference.whereNotEqualTo(field, value))
    actual fun whereNotEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(collectionReference.whereNotEqualTo(fieldPath.android, value))

    actual fun whereArrayContains(field: String, value: Any) =
        Query(collectionReference.whereArrayContains(field, value))

    actual fun whereArrayContains(fieldPath: FieldPath, value: Any) =
        Query(collectionReference.whereEqualTo(fieldPath.android, value))

    actual fun whereGreaterThan(field: String, value: Any) =
        Query(collectionReference.whereGreaterThan(field, value))

    actual fun whereGreaterThan(fieldPath: FieldPath, value: Any) =
        Query(collectionReference.whereGreaterThan(fieldPath.android, value))

    actual fun whereLessThan(field: String, value: Any) =
        Query(collectionReference.whereLessThan(field, value))

    actual fun whereLessThan(fieldPath: FieldPath, value: Any) =
        Query(collectionReference.whereLessThan(fieldPath.android, value))

    actual fun whereIn(field: String, values: MutableList<Any>) =
        Query(collectionReference.whereIn(field, values))
    actual fun whereIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(collectionReference.whereIn(fieldPath.android, values))

    actual fun whereArrayContainsAny(field: String, values: MutableList<Any>) =
        Query(collectionReference.whereArrayContainsAny(field, values))
    actual fun whereArrayContainsAny(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(collectionReference.whereArrayContainsAny(fieldPath.android, values))

    actual fun whereGreaterThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(collectionReference.whereGreaterThanOrEqualTo(field, values))
    actual fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(collectionReference.whereGreaterThanOrEqualTo(fieldPath.android, values))

    actual fun whereLessThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(collectionReference.whereLessThanOrEqualTo(field, values))
    actual fun whereLessThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(collectionReference.whereLessThanOrEqualTo(fieldPath.android, values))

    actual fun whereNotIn(field: String, values: MutableList<Any>) =
        Query(collectionReference.whereNotIn(field, values))
    actual fun whereNotIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(collectionReference.whereNotIn(fieldPath.android, values))






    override fun toString(): String = collectionReference.toString()
    override fun hashCode(): Int = collectionReference.hashCode()
    override fun equals(other: Any?): Boolean =
        other is CollectionReference && collectionReference == other.collectionReference
}

actual class DocumentReference actual constructor(actual val documentReference: com.google.firebase.firestore.DocumentReference?) {

}

actual class WriteBatch actual constructor(actual val batch: com.google.firebase.firestore.WriteBatch) {

}

actual class Query actual constructor(actual val query: com.google.firebase.firestore.Query) {

}

actual class Transaction actual constructor(actual val transaction: com.google.firebase.firestore.Transaction) {

}

actual class QuerySnapshot actual constructor(actual val querySnapshot: com.google.firebase.firestore.QuerySnapshot) {

}

actual class AggregateQuery actual constructor(actual val aggregateQuery: com.google.firebase.firestore.AggregateQuery) {

}

actual class FieldPath actual constructor(actual val android: com.google.firebase.firestore.FieldPath) {

    override fun toString(): String = android.toString()
    override fun hashCode(): Int = android.hashCode()
    override fun equals(other: Any?): Boolean = other is FieldPath && android == other.android
}


actual class DocumentSnapshot actual constructor(actual val android: com.google.firebase.firestore.DocumentSnapshot) {

}

actual class Filter actual constructor(actual val android: com.google.firebase.firestore.Filter) {

}
