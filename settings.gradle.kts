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
include(":app")
include(":firebase-app")
include(":firebase-auth")
include(":firebase-config")
include(":firebase-crashlytics")
include(":firebase-firestore")
include(":firebase-installations")
include(":firebase-messaging")
include(":firebase-performance")
include(":firebase-storage")
