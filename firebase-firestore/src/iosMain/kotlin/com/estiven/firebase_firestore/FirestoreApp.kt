package com.estiven.firebase_firestore

actual class FirestoreApp(private val iOS: NativeFirestoreApp) {
    actual val name
        get() = iOS.name
    actual val isDataCollectionDefaultEnabled
        get() = iOS.isDataCollectionDefaultEnabled()
    actual val options
        get() = FirestoreOptions(iOS.options)
}