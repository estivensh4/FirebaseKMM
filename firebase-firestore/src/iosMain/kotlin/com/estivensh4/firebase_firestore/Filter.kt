/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRFilter

actual class Filter(val iOS: FIRFilter) {
    override fun toString(): String = iOS.toString()
    override fun hashCode(): Int = iOS.hashCode()
    override fun equals(other: Any?): Boolean = other is Filter && iOS == other.iOS
}