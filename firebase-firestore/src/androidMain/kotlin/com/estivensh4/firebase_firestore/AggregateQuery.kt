package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.AggregateQuery

actual class AggregateQuery(val android: AggregateQuery) {
    actual val query
        get() = Query(android.query)
}