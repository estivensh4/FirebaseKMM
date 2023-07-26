/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentSnapshot
import cocoapods.FirebaseFirestore.FIRServerTimestampBehavior
import com.estivensh4.firebase_common.decode
import kotlinx.serialization.DeserializationStrategy
import platform.Foundation.NSNull

actual class DocumentSnapshot(val iOS: FIRDocumentSnapshot) {
    actual val exists get() = iOS.exists
    actual inline fun <reified T> get(field: String, serverTimestampBehavior: ServerTimestampBehavior): T {
        val value = iOS.valueForField(field, serverTimestampBehavior.toIos())?.takeIf { it !is NSNull }
        return decode(value)
    }

    actual fun <T> get(field: String, strategy: DeserializationStrategy<T>, serverTimestampBehavior: ServerTimestampBehavior): T {
        val value = iOS.valueForField(field, serverTimestampBehavior.toIos())?.takeIf { it !is NSNull }
        return decode(strategy, value)
    }

    actual inline fun <reified T: Any> getData(serverTimestampBehavior: ServerTimestampBehavior): T {
        val data = iOS.dataWithServerTimestampBehavior(serverTimestampBehavior.toIos())
        return decode(value = data?.mapValues { (_, value) -> value?.takeIf { it !is NSNull } })
    }

    actual fun <T> getData(strategy: DeserializationStrategy<T>, serverTimestampBehavior: ServerTimestampBehavior): T {
        val data = iOS.dataWithServerTimestampBehavior(serverTimestampBehavior.toIos())
        return decode(strategy, data?.mapValues { (_, value) -> value?.takeIf { it !is NSNull } })
    }

    fun ServerTimestampBehavior.toIos() : FIRServerTimestampBehavior = when (this) {
        ServerTimestampBehavior.ESTIMATE -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorEstimate
        ServerTimestampBehavior.NONE -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorNone
        ServerTimestampBehavior.PREVIOUS -> FIRServerTimestampBehavior.FIRServerTimestampBehaviorPrevious
    }
}