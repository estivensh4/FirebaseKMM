/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

expect class WriteBatch {
    fun set(documentRef: DocumentReference, data: Any): WriteBatch
    fun update(
        documentRef: DocumentReference, data: MutableMap<String, Any>
    ): WriteBatch

    fun delete(
        documentRef: DocumentReference
    ): WriteBatch

    suspend fun commit()
}