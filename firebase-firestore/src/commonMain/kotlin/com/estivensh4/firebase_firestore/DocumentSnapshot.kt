/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import kotlinx.serialization.DeserializationStrategy

expect class DocumentSnapshot {
    val exists: Boolean
    inline fun <reified T> get(field: String, serverTimestampBehavior: ServerTimestampBehavior = ServerTimestampBehavior.NONE): T
    fun <T> get(field: String, strategy: DeserializationStrategy<T>, serverTimestampBehavior: ServerTimestampBehavior = ServerTimestampBehavior.NONE): T
    fun <T> getData(
        strategy: DeserializationStrategy<T>,
        serverTimestampBehavior: ServerTimestampBehavior = ServerTimestampBehavior.NONE
    ): T

    inline fun <reified T : Any> getData(serverTimestampBehavior: ServerTimestampBehavior = ServerTimestampBehavior.NONE): T
}