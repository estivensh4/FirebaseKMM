package com.estivensh4.firebase_firestore

actual class FirestoreApp(val android: com.google.firebase.FirebaseApp) {
    actual val name
        get() = android.name
    actual val isDataCollectionDefaultEnabled
        get() = android.isDataCollectionDefaultEnabled
    actual val options
        get() = FirestoreOptions(android.options)
}