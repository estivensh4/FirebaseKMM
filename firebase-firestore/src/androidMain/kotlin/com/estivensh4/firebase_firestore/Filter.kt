/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:09
 *
 */

package com.estivensh4.firebase_firestore

actual class Filter(val android: com.google.firebase.firestore.Filter) {
    override fun toString(): String = android.toString()
    override fun hashCode(): Int = android.hashCode()
    override fun equals(other: Any?): Boolean = other is Filter && android == other.android
}