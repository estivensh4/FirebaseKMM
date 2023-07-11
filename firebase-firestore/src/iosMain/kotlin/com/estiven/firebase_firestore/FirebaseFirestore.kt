package com.estiven.firebase_firestore

import cocoapods.FirebaseCore.FIRApp
import cocoapods.FirebaseCore.FIROptions
import cocoapods.FirebaseFirestore.*
import com.estiven.firebase_app.Firebase

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



actual class Transaction(private val iOS: NativeTransaction) {
    @Suppress("UNCHECKED_CAST")
    actual fun set(documentRef: DocumentReference, data: Any) =
        Transaction(iOS.setData(data as Map<Any?, *>, documentRef.iOS))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        Transaction(iOS.setData(data.toMap(), documentRef.iOS))

    actual suspend fun delete(documentRef: DocumentReference) = await { documentRef.delete() }
    actual suspend fun get(documentRef: DocumentReference) = DocumentSnapshot(documentRef.get().iOS)
}














