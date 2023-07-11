package com.estiven.firebase_firestore

actual class AggregateQuery(val aggregateQuery: NativeAggregateQuery) {
    actual val query
        get() = aggregateQuery.query
}