package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore as firebaseFirestore

actual val Firebase.firestore
    get() = FirebaseFirestore(firebaseFirestore.getInstance())

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

actual class FirebaseFirestore(private val android: firebaseFirestore) {

    actual val firestoreSettings
        get() = FirestoreSettings(android.firestoreSettings)
    //actual val app
      //  get() = FirestoreApp(android.app)

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