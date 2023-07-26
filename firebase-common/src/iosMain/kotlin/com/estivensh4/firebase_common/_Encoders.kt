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
import kotlin.collections.set

@OptIn(ExperimentalSerializationApi::class)
actual fun FirebaseEncoder.structureEncoder(descriptor: SerialDescriptor): FirebaseCompositeEncoder =
    when (descriptor.kind) {
        StructureKind.LIST -> mutableListOf<Any?>()
            .also { value = it }
            .let {
                FirebaseCompositeEncoder(shouldEncodeElementDefault) { _, index, value ->
                    it.add(
                        index,
                        value
                    )
                }
            }

        StructureKind.MAP -> mutableListOf<Any?>()
            .let {
                FirebaseCompositeEncoder(
                    shouldEncodeElementDefault,
                    {
                        value = it.chunked(2).associate { (k, v) -> k to v }
                    }) { _, _, value -> it.add(value) }
            }

        StructureKind.CLASS, StructureKind.OBJECT, PolymorphicKind.SEALED -> mutableMapOf<Any?, Any?>()
            .also { value = it }
            .let {
                FirebaseCompositeEncoder(shouldEncodeElementDefault,
                    setPolymorphicType = { discriminator, type ->
                        it[discriminator] = type
                    },
                    set = { _, index, value -> it[descriptor.getElementName(index)] = value }
                )
            }

        else -> TODO("The firebase-kotlin-sdk does not support $descriptor for serialization yet")
    }