/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import kotlinx.coroutines.tasks.await

actual class WriteBatch(val android: com.google.firebase.firestore.WriteBatch) {
    actual fun set(documentRef: DocumentReference, data: Any) =
        WriteBatch(android.set(documentRef.android, data))

    actual fun update(documentRef: DocumentReference, data: MutableMap<String, Any>) =
        WriteBatch(android.update(documentRef.android, data))

    actual fun delete(documentRef: DocumentReference) =
        WriteBatch(android.delete(documentRef.android))

    actual suspend fun commit() = android.commit().await().run { }
}