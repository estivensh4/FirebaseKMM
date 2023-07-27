/*
 * *
 *  * Created by estiven on 26/7/23 23:52
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 26/7/23 23:52
 *
 */

package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.FieldValue

actual object FieldValue {
    actual val serverTimestamp = Double.POSITIVE_INFINITY
    actual val delete: Any get() = FieldValue.delete()
    actual fun increment(value: Int): Any = FieldValue.increment(value.toDouble())
    actual fun arrayUnion(vararg elements: Any): Any = FieldValue.arrayUnion(*elements)
    actual fun arrayRemove(vararg elements: Any): Any = FieldValue.arrayRemove(*elements)
    actual fun delete(): Any = delete
}