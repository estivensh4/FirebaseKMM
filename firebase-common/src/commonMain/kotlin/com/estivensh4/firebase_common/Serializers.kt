/*
 * *
 *  * Created by estiven on 26/7/23 15:02
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 15/7/23 00:37
 *
 */

package com.estivensh4.firebase_common

import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@OptIn(InternalSerializationApi::class)
@ExperimentalSerializationApi
@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> T.firebaseSerializer() = runCatching { serializer<T>() }
    .getOrElse {
        when (this) {
            is Map<*, *> -> FirebaseMapSerializer()
            is List<*> -> FirebaseListSerializer()
            is Set<*> -> FirebaseListSerializer()
            else -> this::class.serializer()
        } as SerializationStrategy<T>
    }

@ExperimentalSerializationApi
class FirebaseMapSerializer : KSerializer<Map<String, Any?>> {

    lateinit var keys: List<String>
    lateinit var map: Map<String, Any?>

    override val descriptor = object : SerialDescriptor {
        override val kind = StructureKind.MAP
        override val serialName = "kotlin.Map<String, Any>"
        override val elementsCount get() = map.size
        override fun getElementIndex(name: String) = keys.indexOf(name)
        override fun getElementName(index: Int) = keys[index]
        override fun getElementAnnotations(index: Int) = emptyList<Annotation>()
        override fun getElementDescriptor(index: Int) = throw NotImplementedError()
        override fun isElementOptional(index: Int) = false
    }

    @Suppress("UNCHECKED_CAST")
    override fun serialize(encoder: Encoder, value: Map<String, Any?>) {
        map = value
        keys = value.keys.toList()
        val collectionEncoder = encoder.beginCollection(descriptor, value.size)
        keys.forEachIndexed { index, key ->
            val listValue = map.getValue(key)
            val serializer =
                (listValue?.firebaseSerializer() ?: Unit.serializer()) as KSerializer<Any?>
            String.serializer().let {
                collectionEncoder.encodeSerializableElement(it.descriptor, index * 2, it, key)
            }
            collectionEncoder.encodeNullableSerializableElement(
                serializer.descriptor, index * 2 + 1, serializer, listValue
            )
        }
        collectionEncoder.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): Map<String, Any?> {
        val collectionDecoder = decoder.beginStructure(descriptor) as FirebaseCompositeDecoder
        val map = mutableMapOf<String, Any?>()
        for (index in 0 until collectionDecoder.decodeCollectionSize(descriptor) * 2 step 2) {
            //
        }
        return map
    }
}

class FirebaseListSerializer : KSerializer<Iterable<Any?>> {

    lateinit var list: List<Any?>

    @ExperimentalSerializationApi
    override val descriptor = object : SerialDescriptor {
        override val kind = StructureKind.LIST
        override val serialName = "kotlin.List<Any>"
        override val elementsCount get() = list.size
        override fun getElementIndex(name: String) = throw NotImplementedError()
        override fun getElementName(index: Int) = throw NotImplementedError()
        override fun getElementAnnotations(index: Int) = emptyList<Annotation>()
        override fun getElementDescriptor(index: Int) = throw NotImplementedError()
        override fun isElementOptional(index: Int) = false
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("UNCHECKED_CAST")
    override fun serialize(encoder: Encoder, value: Iterable<Any?>) {
        list = value.toList()
        val collectionEncoder = encoder.beginCollection(descriptor, list.size)
        list.forEachIndexed { index, listValue ->
            val serializer =
                (listValue?.firebaseSerializer() ?: Unit.serializer()) as KSerializer<Any>
            collectionEncoder.encodeNullableSerializableElement(
                serializer.descriptor, index, serializer, listValue
            )
        }
        collectionEncoder.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): List<Any?> {
        throw NotImplementedError()
    }
}

/**
 * A special case of serializer for values natively supported by Firebase and
 * don't require an additional encoding/decoding.
 */
class SpecialValueSerializer<T>(
    serialName: String,
    private val toNativeValue: (T) -> Any?,
    private val fromNativeValue: (Any?) -> T
) : KSerializer<T> {
    override val descriptor = buildClassSerialDescriptor(serialName) { }

    override fun serialize(encoder: Encoder, value: T) {
        if (encoder is FirebaseEncoder) {
            encoder.value = toNativeValue(value)
        } else {
            throw SerializationException("This serializer must be used with FirebaseEncoder")
        }
    }

    override fun deserialize(decoder: Decoder): T {
        return if (decoder is FirebaseDecoder) {
            fromNativeValue(decoder.value)
        } else {
            throw SerializationException("This serializer must be used with FirebaseDecoder")
        }
    }
}
