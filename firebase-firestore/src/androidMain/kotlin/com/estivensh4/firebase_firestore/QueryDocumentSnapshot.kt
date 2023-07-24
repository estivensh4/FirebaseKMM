/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

actual class QueryDocumentSnapshot(val android: com.google.firebase.firestore.QueryDocumentSnapshot) {
    actual inline fun <reified T : Any> toObject() = android.toObject(T::class.java)
}