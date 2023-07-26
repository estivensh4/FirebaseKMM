/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:09
 *
 */

package com.estivensh4.firebase_firestore

import com.estivensh4.firebase_common.decode
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.DeserializationStrategy

actual class DocumentSnapshot(val android: DocumentSnapshot) {
    actual val exists get() = android.exists()
    actual inline fun <reified T> get(
        field: String,
        serverTimestampBehavior: ServerTimestampBehavior
    ): T =
        decode(value = android.get(field, serverTimestampBehavior.toAndroid()))

    actual fun <T> get(
        field: String,
        strategy: DeserializationStrategy<T>,
        serverTimestampBehavior: ServerTimestampBehavior
    ): T =
        decode(strategy, android.get(field, serverTimestampBehavior.toAndroid()))

    actual inline fun <reified T : Any> getData(serverTimestampBehavior: ServerTimestampBehavior): T =
        decode(value = android.getData(serverTimestampBehavior.toAndroid()))

    actual fun <T> getData(
        strategy: DeserializationStrategy<T>,
        serverTimestampBehavior: ServerTimestampBehavior
    ): T =
        decode(strategy, android.getData(serverTimestampBehavior.toAndroid()))

    fun ServerTimestampBehavior.toAndroid(): DocumentSnapshot.ServerTimestampBehavior =
        when (this) {
            ServerTimestampBehavior.ESTIMATE -> DocumentSnapshot.ServerTimestampBehavior.ESTIMATE
            ServerTimestampBehavior.NONE -> DocumentSnapshot.ServerTimestampBehavior.NONE
            ServerTimestampBehavior.PREVIOUS -> DocumentSnapshot.ServerTimestampBehavior.PREVIOUS
        }
}