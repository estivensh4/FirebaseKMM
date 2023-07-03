package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase
import com.estiven.firebase_app.FirebaseOptions


expect val Firebase.firestore: FirebaseFirestore

expect class NativeFirestore

expect class NativeFirestoreSettings

expect class NativeFirestoreApp

expect interface NativeCacheSettings

expect class NativeFirestoreOptions

expect class FirebaseFirestore internal constructor(val nativeValue: NativeFirestore){
    val firestoreSettings: FirestoreSettings
    val app: FirestoreApp
}

expect class FirestoreSettings internal constructor(val firestoreSettings: NativeFirestoreSettings){
    val host: String
    val isSslEnabled: Boolean
    val cacheSettings: CacheSettings
    val cacheSizeBytes: Long
    val isPersistenceEnabled: Boolean
}

expect class CacheSettings internal constructor(val cacheSettings: NativeCacheSettings){

}

expect class FirestoreApp internal constructor(val androidApp: NativeFirestoreApp){
    val name: String
    val isDataCollectionDefaultEnabled: Boolean
    val isDefaultApp: Boolean
    val persistenceKey: String?
    val options: FirestoreOptions
    val applicationContext: Any
}

expect class FirestoreOptions internal constructor(val firebaseOptions: NativeFirestoreOptions){
    val apiKey: String
    val applicationId: String
    val databaseUrl: String?
    val gaTrackingId: String?
    val gcmSenderId: String?
    val storageBucket: String?
    val projectId: String?
}

