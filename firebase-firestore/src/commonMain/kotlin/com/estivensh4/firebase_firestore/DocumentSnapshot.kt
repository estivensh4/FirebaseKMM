/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

expect class DocumentSnapshot {
    fun get(field: String): Any?
    fun get(fieldPath: FieldPath): Any?
    fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior): Any?
    fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior): Any?
    inline fun <reified T : Any> toObject(): T?
}