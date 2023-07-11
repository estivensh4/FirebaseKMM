package com.estiven.firebase_firestore

expect class QueryDocumentSnapshot {
    inline fun <reified T : Any> toObject(): T
}