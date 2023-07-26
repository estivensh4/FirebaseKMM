/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 22:42
 *
 */

import com.estivensh4.buildsrc.Module
import com.estivensh4.buildsrc.ProjectConfig

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

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        ios.deploymentTarget = ProjectConfig.iOS.deploymentTarget
        framework {
            baseName = ProjectConfig.iOS.storageBaseName
        }
        noPodspec()
        pod(ProjectConfig.iOS.storagePod) {
            version = ProjectConfig.iOS.firebaseVersion
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.kotlin.serialization)
                api(project(Module.app))
                api(project(Module.common))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.firebase.storage)
            }
        }
    }
}

android {
    namespace = ProjectConfig.Android.storageModule
    compileSdk = ProjectConfig.Android.compileSdk
    defaultConfig {
        minSdk = ProjectConfig.Android.minSdk
    }
}

signing {
    val signingKey: String? by project
    val signingPassword: String? by project
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
}