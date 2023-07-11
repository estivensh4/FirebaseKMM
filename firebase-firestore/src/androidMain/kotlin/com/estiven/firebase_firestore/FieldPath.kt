package com.estiven.firebase_firestore

actual class FieldPath(val android: com.google.firebase.firestore.FieldPath) {
    override fun toString(): String = android.toString()
    override fun hashCode(): Int = android.hashCode()
    override fun equals(other: Any?): Boolean = other is FieldPath && android == other.android
}