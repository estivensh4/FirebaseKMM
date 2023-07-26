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
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

inline fun <reified T> decode(value: Any?): T {
    val strategy = serializer<T>()
    return decode(strategy as DeserializationStrategy<T>, value)
}

@OptIn(ExperimentalSerializationApi::class)
fun <T> decode(strategy: DeserializationStrategy<T>, value: Any?): T {
    require(value != null || strategy.descriptor.isNullable) { "Value was null for non-nullable type ${strategy.descriptor.serialName}" }
    return FirebaseDecoder(value).decodeSerializableValue(strategy)
}

expect fun FirebaseDecoder.structureDecoder(descriptor: SerialDescriptor): CompositeDecoder
expect fun getPolymorphicType(value: Any?, discriminator: String): String

class FirebaseDecoder(internal val value: Any?) : Decoder {

    override val serializersModule: SerializersModule
        get() = EmptySerializersModule()

    override fun beginStructure(descriptor: SerialDescriptor) = structureDecoder(descriptor)

    override fun decodeString() = decodeString(value)

    override fun decodeDouble() = decodeDouble(value)

    override fun decodeLong() = decodeLong(value)

    override fun decodeByte() = decodeByte(value)

    override fun decodeFloat() = decodeFloat(value)

    override fun decodeInt() = decodeInt(value)

    override fun decodeShort() = decodeShort(value)

    override fun decodeBoolean() = decodeBoolean(value)

    override fun decodeChar() = decodeChar(value)

    override fun decodeEnum(enumDescriptor: SerialDescriptor) = decodeEnum(value, enumDescriptor)

    @ExperimentalSerializationApi
    override fun decodeNotNullMark() = decodeNotNullMark(value)

    @ExperimentalSerializationApi
    override fun decodeNull() = decodeNull(value)

    override fun decodeInline(descriptor: SerialDescriptor) = FirebaseDecoder(value)

    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
        return decodeSerializableValuePolymorphic(value, deserializer)
    }
}

class FirebaseClassDecoder(
    size: Int,
    private val containsKey: (name: String) -> Boolean,
    get: (descriptor: SerialDescriptor, index: Int) -> Any?
) : FirebaseCompositeDecoder(size, get) {
    private var index: Int = 0

    @ExperimentalSerializationApi
    override fun decodeSequentially() = false

    @OptIn(ExperimentalSerializationApi::class)
    override fun decodeElementIndex(descriptor: SerialDescriptor): Int =
        (index until descriptor.elementsCount)
            .firstOrNull {
                !descriptor.isElementOptional(it) || containsKey(
                    descriptor.getElementName(
                        it
                    )
                )
            }
            ?.also { index = it + 1 }
            ?: DECODE_DONE
}

open class FirebaseCompositeDecoder(
    private val size: Int,
    private val get: (descriptor: SerialDescriptor, index: Int) -> Any?
) : CompositeDecoder {

    override val serializersModule = EmptySerializersModule()

    @ExperimentalSerializationApi
    override fun decodeSequentially() = true

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int = throw NotImplementedError()

    override fun decodeCollectionSize(descriptor: SerialDescriptor) = size

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ) = deserializer.deserialize(FirebaseDecoder(get(descriptor, index)))

    override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int) =
        decodeBoolean(get(descriptor, index))

    override fun decodeByteElement(descriptor: SerialDescriptor, index: Int) =
        decodeByte(get(descriptor, index))

    override fun decodeCharElement(descriptor: SerialDescriptor, index: Int) =
        decodeChar(get(descriptor, index))

    override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int) =
        decodeDouble(get(descriptor, index))

    override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int) =
        decodeFloat(get(descriptor, index))

    override fun decodeIntElement(descriptor: SerialDescriptor, index: Int) =
        decodeInt(get(descriptor, index))

    override fun decodeLongElement(descriptor: SerialDescriptor, index: Int) =
        decodeLong(get(descriptor, index))

    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        val isNullabilitySupported = deserializer.descriptor.isNullable
        return if (isNullabilitySupported || decodeNotNullMark(
                get(
                    descriptor,
                    index
                )
            )
        ) decodeSerializableElement(descriptor, index, deserializer, previousValue) else decodeNull(
            get(descriptor, index)
        )
    }

    override fun decodeShortElement(descriptor: SerialDescriptor, index: Int) =
        decodeShort(get(descriptor, index))

    override fun decodeStringElement(descriptor: SerialDescriptor, index: Int) =
        decodeString(get(descriptor, index))

    override fun endStructure(descriptor: SerialDescriptor) {}

    @ExperimentalSerializationApi
    override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder =
        FirebaseDecoder(get(descriptor, index))

}

private fun decodeString(value: Any?) = value.toString()

private fun decodeDouble(value: Any?) = when (value) {
    is Number -> value.toDouble()
    is String -> value.toDouble()
    else -> throw SerializationException("Expected $value to be double")
}

private fun decodeLong(value: Any?) = when (value) {
    is Number -> value.toLong()
    is String -> value.toLong()
    else -> throw SerializationException("Expected $value to be long")
}

private fun decodeByte(value: Any?) = when (value) {
    is Number -> value.toByte()
    is String -> value.toByte()
    else -> throw SerializationException("Expected $value to be byte")
}

private fun decodeFloat(value: Any?) = when (value) {
    is Number -> value.toFloat()
    is String -> value.toFloat()
    else -> throw SerializationException("Expected $value to be float")
}

private fun decodeInt(value: Any?) = when (value) {
    is Number -> value.toInt()
    is String -> value.toInt()
    else -> throw SerializationException("Expected $value to be int")
}

private fun decodeShort(value: Any?) = when (value) {
    is Number -> value.toShort()
    is String -> value.toShort()
    else -> throw SerializationException("Expected $value to be short")
}

private fun decodeBoolean(value: Any?) = value as Boolean

private fun decodeChar(value: Any?) = when (value) {
    is Number -> value.toChar()
    is String -> value[0]
    else -> throw SerializationException("Expected $value to be char")
}

private fun decodeEnum(value: Any?, enumDescriptor: SerialDescriptor) = when (value) {
    is Number -> value.toInt()
    is String -> enumDescriptor.getElementIndexOrThrow(value)
    else -> throw SerializationException("Expected $value to be enum")
}

@OptIn(ExperimentalSerializationApi::class)
internal fun SerialDescriptor.getElementIndexOrThrow(name: String): Int {
    val index = getElementIndex(name)
    if (index == CompositeDecoder.UNKNOWN_NAME)
        throw SerializationException("$serialName does not contain element with name '$name'")
    return index
}

private fun decodeNotNullMark(value: Any?) = value != null

private fun decodeNull(value: Any?) = value as Nothing?


