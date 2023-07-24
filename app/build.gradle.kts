/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:10
 *
 */

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("android")
}

android {
    namespace = "com.estivensh4.app"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.estivensh4.app"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
val compose = "1.4.3"
    val version = "1.0.2"
    implementation("com.eygraber:uri-kmp:0.0.3")
    implementation(project(":firebase-storage"))
    implementation(project(":firebase-firestore"))
    implementation(project(":firebase-auth"))
    implementation(project(":firebase-storage"))
    implementation(project(":firebase-config"))
    implementation(project(":firebase-crashlytics"))
    implementation(project(":firebase-performance"))
    implementation(project(":firebase-messaging"))
    //implementation("io.github.estivensh4:firebase-app:0.0.1")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:${compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${compose}")
    implementation("androidx.compose.material3:material3:1.0.0-alpha11")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${compose}")
    implementation(platform("com.google.firebase:firebase-bom:32.1.1"))
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-config-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")
    //implementation("com.google.firebase:firebase-perf-ktx")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("io.getstream:stream-webrtc-android-compose:$version")
}

