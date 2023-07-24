package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRAggregateQuery

actual class AggregateQuery(private val iOS: FIRAggregateQuery) {
    actual val query
        get() = Query(iOS.query)
}