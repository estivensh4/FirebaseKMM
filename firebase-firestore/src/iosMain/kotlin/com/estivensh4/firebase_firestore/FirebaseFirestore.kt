/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:25
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.*
import com.estivensh4.firebase_app.Firebase
import com.estivensh4.firebase_common.await
import com.estivensh4.firebase_common.awaitResult

actual val Firebase.firestore
    get() = FirebaseFirestore(FIRFirestore.firestore())

actual typealias Source = FIRFirestoreSource

actual class FirebaseFirestore(private val iOS: FIRFirestore) {
    actual val firestoreSettings
        get() = FirestoreSettings(iOS.settings)

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

actual class Transaction(private val iOS: FIRTransaction) {
    @Suppress("UNCHECKED_CAST")
    actual fun set(documentRef: DocumentReference, data: Any) =
        Transaction(iOS.setData(data as Map<Any?, *>, documentRef.iOS))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        Transaction(iOS.setData(data.toMap(), documentRef.iOS))

    actual suspend fun delete(documentRef: DocumentReference) {
        await { documentRef.delete() }
    }
    actual suspend fun get(documentRef: DocumentReference) = DocumentSnapshot(documentRef.get().iOS)
}














