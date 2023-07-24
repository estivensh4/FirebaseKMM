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