package com.estiven.firebase_firestore

expect class DocumentSnapshot {
    fun get(field: String): Any?
    fun get(fieldPath: FieldPath): Any?
    fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior): Any?
    fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior): Any?
    inline fun <reified T : Any> toObject(): T
}