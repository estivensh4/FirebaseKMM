/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRDocumentChangeType
import kotlinx.coroutines.CompletableDeferred
import platform.Foundation.NSError

fun FIRDocumentChangeType.toIos(): DocumentType {
    return when (this) {
        FIRDocumentChangeType.FIRDocumentChangeTypeRemoved -> DocumentType.REMOVED
        FIRDocumentChangeType.FIRDocumentChangeTypeAdded -> DocumentType.ADDED
        FIRDocumentChangeType.FIRDocumentChangeTypeModified -> DocumentType.MODIFIED
        else -> DocumentType.ADDED
    }
}