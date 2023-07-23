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
include(":firebase-firestore")
include(":firebase-app")
include(":firebase-storage")
include(":app")
include(":firebase-auth")
include(":firebase-config")
include(":firebase-crashlytics")
include(":firebase-installations")
