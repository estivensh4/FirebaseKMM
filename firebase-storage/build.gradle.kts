version = project.property("firebase-storage.version") as String

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlinx.kover")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishAllLibraryVariants()
    }

    val supportIosTarget = project.property("skipIosTarget") != "true"

    if (supportIosTarget) {
        iosX64()
        iosArm64()
        iosSimulatorArm64()
        cocoapods {
            summary = "Some description for the Shared Module"
            homepage = "Link to the Shared Module homepage"
            version = "1.0"
            ios.deploymentTarget = "16.1"
            framework {
                baseName = "firebase-storage"
            }
            noPodspec()
            pod("FirebaseStorage") {
                version = "10.11.0"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
                api(project(":firebase-app"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.google.firebase:firebase-storage")
            }
        }
    }
}

android {
    namespace = "com.estiven.firebase_storage"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    //useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}

if (project.property("firebase-storage.skipIosTests") == "true") {
    tasks.forEach {
        if (it.name.contains("ios", true) && it.name.contains("test", true)) { it.enabled = false }
    }
}