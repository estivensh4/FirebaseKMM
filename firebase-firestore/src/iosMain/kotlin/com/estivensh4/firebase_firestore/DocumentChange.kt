/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentChange

actual class DocumentChange(private val iOS: FIRDocumentChange) {
    actual val newIndex get() = iOS.newIndex.toInt()
    actual val oldIndex get() = iOS.oldIndex.toInt()
    actual val document get() = QueryDocumentSnapshot(iOS.document)
    actual val type get() = iOS.type.toIos()
}