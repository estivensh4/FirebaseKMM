package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRFilter

actual class Filter(val iOS: FIRFilter) {
    override fun toString(): String = iOS.toString()
    override fun hashCode(): Int = iOS.hashCode()
    override fun equals(other: Any?): Boolean = other is Filter && iOS == other.iOS
}