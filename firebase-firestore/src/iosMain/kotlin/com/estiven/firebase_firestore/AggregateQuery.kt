package com.estiven.firebase_firestore

actual class AggregateQuery(private val iOS: NativeAggregateQuery) {
    actual val query
        get() = iOS.query
}