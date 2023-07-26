/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 21:34
 *
 */

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FirebaseKMM"
include(":firebase-app")
include(":firebase-auth")
include(":firebase-config")
include(":firebase-crashlytics")
include(":firebase-firestore")
include(":firebase-installations")
include(":firebase-messaging")
include(":firebase-performance")
include(":firebase-storage")
include(":firebase-common")
