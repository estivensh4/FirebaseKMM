/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

actual class FirestoreApp(val android: com.google.firebase.FirebaseApp) {
    actual val name
        get() = android.name
    actual val isDataCollectionDefaultEnabled
        get() = android.isDataCollectionDefaultEnabled
    actual val options
        get() = FirestoreOptions(android.options)
}