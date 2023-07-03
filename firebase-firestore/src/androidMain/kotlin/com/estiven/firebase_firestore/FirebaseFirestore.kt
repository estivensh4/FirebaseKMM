package com.estiven.firebase_firestore

import com.estiven.firebase_app.Firebase
import com.google.firebase.FirebaseOptions
import com.google.firebase.firestore.FirebaseFirestoreSettings
import io.grpc.Context
import org.jetbrains.annotations.Contract


actual val Firebase.firestore
    get() = FirebaseFirestore(com.google.firebase.firestore.FirebaseFirestore.getInstance())


actual typealias  NativeFirestore = com.google.firebase.firestore.FirebaseFirestore

actual class FirebaseFirestore actual constructor(actual val nativeValue: com.google.firebase.firestore.FirebaseFirestore){
    actual val firestoreSettings
        get() = FirestoreSettings(nativeValue.firestoreSettings)
    actual val app
        get() = FirestoreApp(nativeValue.app)
}

actual typealias NativeFirestoreSettings = FirebaseFirestoreSettings

actual typealias NativeCacheSettings = com.google.firebase.firestore.LocalCacheSettings

actual typealias NativeFirestoreApp = com.google.firebase.FirebaseApp

actual typealias NativeFirestoreOptions = com.google.firebase.FirebaseOptions

actual class FirestoreSettings actual constructor(actual val firestoreSettings: FirebaseFirestoreSettings){
    actual val host
        get() = firestoreSettings.host
    actual val isSslEnabled
        get() = firestoreSettings.isSslEnabled
    actual val cacheSettings
        get() = CacheSettings(firestoreSettings.cacheSettings!!)
    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val cacheSizeBytes
        get() = firestoreSettings.cacheSizeBytes
    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val isPersistenceEnabled
        get() = firestoreSettings.isPersistenceEnabled
}

actual class CacheSettings actual constructor(actual val cacheSettings: com.google.firebase.firestore.LocalCacheSettings){

}

actual class FirestoreApp actual constructor(actual val androidApp: com.google.firebase.FirebaseApp){
    actual val name
        get() = androidApp.name
    actual val isDataCollectionDefaultEnabled
        get() = androidApp.isDataCollectionDefaultEnabled
    actual val isDefaultApp
        get() = androidApp.isDefaultApp
    actual val persistenceKey: String?
        get() = androidApp.persistenceKey
    actual val options
        get() = FirestoreOptions(androidApp.options)
    actual val applicationContext: Any
        get() = androidApp.applicationContext
}

actual class FirestoreOptions actual constructor(actual val firebaseOptions: FirebaseOptions){
    actual val apiKey
        get() = firebaseOptions.apiKey
    actual val applicationId
        get() = firebaseOptions.applicationId
    actual val databaseUrl
        get() = firebaseOptions.databaseUrl
    actual val gaTrackingId
        get() = firebaseOptions.gaTrackingId
    actual val gcmSenderId
        get() = firebaseOptions.gcmSenderId
    actual val storageBucket
        get() = firebaseOptions.storageBucket
    actual val projectId
        get() = firebaseOptions.projectId
}


