package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentSnapshot

actual class QueryDocumentSnapshot(val iOS: FIRDocumentSnapshot) {
    actual inline fun <reified T : Any> toObject() = iOS.data() as T
}