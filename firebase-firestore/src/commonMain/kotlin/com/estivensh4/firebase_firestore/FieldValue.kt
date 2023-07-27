/*
 * *
 *  * Created by estiven on 26/7/23 23:49
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 26/7/23 23:49
 *
 */

package com.estivensh4.firebase_firestore

import com.estivensh4.firebase_common.SpecialValueSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException

@Serializable(with = FieldValueSerializer::class)
expect class FieldValue internal constructor(nativeValue: Any) {
    internal val nativeValue: Any

    companion object {
        val serverTimestamp: FieldValue
        val delete: FieldValue
        fun increment(value: Int): FieldValue
        fun arrayUnion(vararg elements: Any): FieldValue
        fun arrayRemove(vararg elements: Any): FieldValue
    }
}

object FieldValueSerializer : KSerializer<FieldValue> by SpecialValueSerializer(
    serialName = "FieldValue",
    toNativeValue = FieldValue::nativeValue,
    fromNativeValue = { raw ->
        raw?.let(::FieldValue) ?: throw SerializationException("Cannot deserialize $raw")
    }
)
