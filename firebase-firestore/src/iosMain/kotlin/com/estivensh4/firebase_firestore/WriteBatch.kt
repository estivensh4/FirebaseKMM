/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRWriteBatch
import com.estivensh4.firebase_common.await

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