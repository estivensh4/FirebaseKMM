package com.estiven.buildsrc

object ProjectConfig {
    object iOS {
        const val deploymentTarget = "16.1"
        const val firebaseVersion = "10.11.0"

        //
        const val appBaseName = "firebase-app"
        const val authBaseName = "firebase-auth"
        const val configBaseName = "firebase-config"
        const val crashlyticsBaseName = "firebase-crashlytics"
        const val firestoreBaseName = "firebase-firestore"
        const val installationsBaseName = "firebase-installations"
        const val messagingBaseName = "firebase-messaging"
        const val performanceBaseName = "firebase-performance"
        const val storageBaseName = "firebase-storage"

        //
        const val appPod = "FirebaseCore"
        const val authPod = "FirebaseAuth"
        const val configPod = "FirebaseRemoteConfig"
        const val crashlyticsPod = "FirebaseCrashlytics"
        const val firestorePod = "FirebaseFirestore"
        const val installationsPod = "FirebaseInstallations"
        const val messagingPod = "FirebaseMessaging"
        const val performancePod = "FirebasePerformance"
        const val storagePod = "FirebaseStorage"
    }

    object Android {
        const val packageName = "com.estiven"
        const val compileSdk = 33
        const val minSdk = 24
        const val appModule = "$packageName.firebase_app"
        const val authModule = "$packageName.firebase_auth"
        const val configModule = "$packageName.firebase_config"
        const val crashlyticsModule = "$packageName.firebase_crashlytics"
        const val firestoreModule = "$packageName.firebase_firestore"
        const val installationsModule = "$packageName.firebase_installations"
        const val messagingModule = "$packageName.firebase_messaging"
        const val performanceModule = "$packageName.firebase_performance"
        const val storageModule = "$packageName.firebase_storage"
    }
}