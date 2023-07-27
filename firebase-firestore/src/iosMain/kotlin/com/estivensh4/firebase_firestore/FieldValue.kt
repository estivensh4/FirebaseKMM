/*
 * *
 *  * Created by estiven on 26/7/23 23:56
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 26/7/23 23:56
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRFieldValue

private typealias NativeFieldValue = FIRFieldValue

actual object FieldValue {
    actual val serverTimestamp = Double.POSITIVE_INFINITY
    actual val delete: Any get() = FIRFieldValue.fieldValueForDelete()
    actual fun increment(value: Int): Any =
        FIRFieldValue.fieldValueForIntegerIncrement(value.toLong())

    actual fun arrayUnion(vararg elements: Any): Any =
        FIRFieldValue.fieldValueForArrayUnion(elements.asList())

    actual fun arrayRemove(vararg elements: Any): Any =
        FIRFieldValue.fieldValueForArrayRemove(elements.asList())

    actual fun delete(): Any = delete
}