/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseCore.FIRApp

actual class FirestoreApp(private val iOS: FIRApp) {
    actual val name
        get() = iOS.name
    actual val isDataCollectionDefaultEnabled
        get() = iOS.isDataCollectionDefaultEnabled()
    actual val options
        get() = FirestoreOptions(iOS.options)
}