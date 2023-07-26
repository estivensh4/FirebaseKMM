/*
 * *
 *  * Created by estiven on 26/7/23 15:18
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 26/7/23 15:15
 *
 */

package com.estivensh4.firebase_common

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder

@OptIn(ExperimentalSerializationApi::class)
actual fun FirebaseDecoder.structureDecoder(descriptor: SerialDescriptor): CompositeDecoder =
    when (descriptor.kind) {
        StructureKind.CLASS, StructureKind.OBJECT, PolymorphicKind.SEALED -> (value as Map<*, *>).let { map ->
            FirebaseClassDecoder(map.size, { map.containsKey(it) }) { desc, index ->
                val elementName = desc.getElementName(index)
                if (desc.kind is PolymorphicKind && elementName == "value") {
                    map
                } else {
                    map[desc.getElementName(index)]
                }
            }
        }

        StructureKind.LIST -> (value as List<*>).let {
            FirebaseCompositeDecoder(it.size) { _, index -> it[index] }
        }

        StructureKind.MAP -> (value as Map<*, *>).entries.toList().let {
            FirebaseCompositeDecoder(it.size) { _, index -> it[index / 2].run { if (index % 2 == 0) key else value } }
        }

        else -> TODO("The firebase-kotlin-sdk does not support $descriptor for serialization yet")
    }

actual fun getPolymorphicType(value: Any?, discriminator: String): String =
    (value as Map<*, *>)[discriminator] as String