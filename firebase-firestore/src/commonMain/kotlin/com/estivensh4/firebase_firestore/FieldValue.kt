/*
 * *
 *  * Created by estiven on 26/7/23 23:49
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 26/7/23 23:49
 *
 */

package com.estivensh4.firebase_firestore

import kotlin.js.JsName

expect object FieldValue {
    val serverTimestamp: Double
    val delete: Any
    fun increment(value: Int): Any
    fun arrayUnion(vararg elements: Any): Any
    fun arrayRemove(vararg elements: Any): Any

    @Deprecated("Replaced with FieldValue.delete")
    @JsName("deprecatedDelete")
    fun delete(): Any
}
