package com.estiven.firebase_firestore

actual class Filter(val iOS: NativeFilter) {
    override fun toString(): String = iOS.toString()
    override fun hashCode(): Int = iOS.hashCode()
    override fun equals(other: Any?): Boolean = other is Filter && iOS == other.iOS
}