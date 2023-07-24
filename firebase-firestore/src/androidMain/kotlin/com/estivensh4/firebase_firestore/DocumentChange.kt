/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:09
 *
 */

package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.DocumentChange

actual class DocumentChange(val android: DocumentChange) {
    actual val newIndex get() = android.newIndex
    actual val oldIndex get() = android.oldIndex
    actual val document get() = QueryDocumentSnapshot(android.document)
    actual val type get() = android.type.toAndroid()
}