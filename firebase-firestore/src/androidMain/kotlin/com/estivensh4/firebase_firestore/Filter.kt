package com.estivensh4.firebase_firestore

actual class Filter(val android: com.google.firebase.firestore.Filter) {
    override fun toString(): String = android.toString()
    override fun hashCode(): Int = android.hashCode()
    override fun equals(other: Any?): Boolean = other is Filter && android == other.android
}