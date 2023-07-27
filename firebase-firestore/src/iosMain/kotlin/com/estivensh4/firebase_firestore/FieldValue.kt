/*
 * *
 *  * Created by estiven on 26/7/23 23:56
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 26/7/23 23:56
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRFieldValue
import kotlinx.serialization.Serializable

private typealias NativeFieldValue = FIRFieldValue

@Serializable(with = FieldValueSerializer::class)
actual class FieldValue internal actual constructor(internal actual val nativeValue: Any) {
    init {
        require(nativeValue is NativeFieldValue)
    }

    override fun equals(other: Any?): Boolean =
        this === other || other is FieldValue && nativeValue == other.nativeValue

    override fun hashCode(): Int = nativeValue.hashCode()
    override fun toString(): String = nativeValue.toString()

    actual companion object {
        actual val serverTimestamp: FieldValue get() = FieldValue(NativeFieldValue.fieldValueForServerTimestamp())
        actual val delete: FieldValue get() = FieldValue(NativeFieldValue.fieldValueForDelete())
        actual fun increment(value: Int): FieldValue =
            FieldValue(NativeFieldValue.fieldValueForIntegerIncrement(value.toLong()))

        actual fun arrayUnion(vararg elements: Any): FieldValue =
            FieldValue(NativeFieldValue.fieldValueForArrayUnion(elements.asList()))

        actual fun arrayRemove(vararg elements: Any): FieldValue =
            FieldValue(NativeFieldValue.fieldValueForArrayRemove(elements.asList()))
    }
}