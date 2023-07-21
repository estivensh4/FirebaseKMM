version = project.property("firebase-firestore.version") as String

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlinx.kover")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    val supportIosTarget = project.property("skipIosTarget") != "true"
    targetHierarchy.default()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "16.1"
        framework {
            baseName = "firebase-firestore"
        }
        noPodspec()
        pod("FirebaseFirestore") {
            version = "10.11.0"
        }
    }
    //}

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":firebase-app"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.code.gson:gson:2.10.1")
                api("com.google.firebase:firebase-firestore")
            }
        }
    }
}

android {
    namespace = "com.estiven.firebase_firestore"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

if (project.property("firebase-firestore.skipIosTests") == "true") {
    tasks.forEach {
        if (it.name.contains("ios", true) && it.name.contains("test", true)) { it.enabled = false }
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    //useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}