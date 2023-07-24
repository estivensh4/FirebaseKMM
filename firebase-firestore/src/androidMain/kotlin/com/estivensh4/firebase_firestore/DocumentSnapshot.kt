/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:09
 *
 */

package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.DocumentSnapshot

actual class DocumentSnapshot(val android: DocumentSnapshot) {
    actual fun get(field: String) = android.get(field)
    actual fun get(fieldPath: FieldPath) = android.get(fieldPath.android)
    actual fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior) =
        android.get(field, serverTimestampBehavior)

    actual fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior) =
        android.get(fieldPath.android, serverTimestampBehavior)

    actual inline fun <reified T : Any> toObject() = android.toObject(T::class.java)
}