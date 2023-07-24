/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

fun com.google.firebase.firestore.DocumentChange.Type.toAndroid(): DocumentType {
    return when (this) {
        com.google.firebase.firestore.DocumentChange.Type.ADDED -> DocumentType.ADDED
        com.google.firebase.firestore.DocumentChange.Type.MODIFIED -> DocumentType.MODIFIED
        com.google.firebase.firestore.DocumentChange.Type.REMOVED -> DocumentType.REMOVED
    }
}