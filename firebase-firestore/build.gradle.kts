import com.estiven.buildsrc.Module
import com.estiven.buildsrc.ProjectConfig

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
        publishAllLibraryVariants()
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    cocoapods {
        ios.deploymentTarget = ProjectConfig.iOS.deploymentTarget
        framework {
            baseName = ProjectConfig.iOS.firestoreBaseName
        }
        noPodspec()
        pod(ProjectConfig.iOS.firestorePod) {
            version = ProjectConfig.iOS.firebaseVersion
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Module.app))
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
    namespace = ProjectConfig.Android.firestoreModule
    compileSdk = ProjectConfig.Android.compileSdk
    defaultConfig {
        minSdk = ProjectConfig.Android.minSdk
    }
}

signing {
    sign(publishing.publications)
}