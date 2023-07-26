/*
 * *
 *  * Created by estiven on 26/7/23 15:02
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 15/7/23 00:37
 *
 */

package com.estivensh4.firebase_common

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.findPolymorphicSerializer
import kotlinx.serialization.internal.AbstractPolymorphicSerializer

/*
 * This code was inspired on polymorphic json serialization of kotlinx.serialization.
 * See https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/json/commonMain/src/kotlinx/serialization/json/internal/Polymorphic.kt
 */
@OptIn(InternalSerializationApi::class)
@Suppress("UNCHECKED_CAST")
internal fun <T> FirebaseEncoder.encodePolymorphically(
    serializer: SerializationStrategy<T>,
    value: T,
    ifPolymorphic: (String) -> Unit
) {
    if (serializer !is AbstractPolymorphicSerializer<*>) {
        serializer.serialize(this, value)
        return
    }
    val casted = serializer as AbstractPolymorphicSerializer<Any>
    val baseClassDiscriminator = serializer.descriptor.classDiscriminator()
    val actualSerializer = casted.findPolymorphicSerializer(this, value as Any)
    ifPolymorphic(baseClassDiscriminator)
    actualSerializer.serialize(this, value)
}

@OptIn(InternalSerializationApi::class)
@Suppress("UNCHECKED_CAST")
internal fun <T> FirebaseDecoder.decodeSerializableValuePolymorphic(
    value: Any?,
    deserializer: DeserializationStrategy<T>,
): T {
    if (deserializer !is AbstractPolymorphicSerializer<*>) {
        return deserializer.deserialize(this)
    }

    val casted = deserializer as AbstractPolymorphicSerializer<Any>
    val discriminator = deserializer.descriptor.classDiscriminator()
    val type = getPolymorphicType(value, discriminator)
    val actualDeserializer = casted.findPolymorphicSerializerOrNull(
        structureDecoder(deserializer.descriptor),
        type
    ) as DeserializationStrategy<T>
    return actualDeserializer.deserialize(this)
}

@OptIn(ExperimentalSerializationApi::class)
internal fun SerialDescriptor.classDiscriminator(): String {
    for (annotation in annotations) {
        if (annotation is FirebaseClassDiscriminator) return annotation.discriminator
    }
    return "type"
}

