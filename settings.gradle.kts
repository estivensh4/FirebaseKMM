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
include(":bom")
include(":firebase-auth")
