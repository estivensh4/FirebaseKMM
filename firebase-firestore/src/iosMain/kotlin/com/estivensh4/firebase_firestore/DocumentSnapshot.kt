/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentSnapshot

actual class DocumentSnapshot(val iOS: FIRDocumentSnapshot) {
    actual fun get(field: String) = iOS.valueForField(field)
    actual fun get(fieldPath: FieldPath) = iOS.valueForField(fieldPath)
    actual fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior) =
        iOS.valueForField(field, serverTimestampBehavior)

    actual fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior) =
        iOS.valueForField(fieldPath, serverTimestampBehavior)

    actual inline fun <reified T : Any> toObject(): T? = iOS.data() as T
}