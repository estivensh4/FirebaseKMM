/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:22
 *
 */

package com.estivensh4.firebase_firestore

import com.estivensh4.firebase_app.Firebase
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.FirebaseFirestore as firebaseFirestore

actual val Firebase.firestore
    get() = FirebaseFirestore(firebaseFirestore.getInstance())

actual typealias Direction = com.google.firebase.firestore.Query.Direction
actual typealias Source = com.google.firebase.firestore.Source
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