version = project.property("firebase-app.version") as String

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlinx.kover")
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
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
                baseName = "firebase-app"
            }
            noPodspec()
            pod("FirebaseCore") {
                version = "10.11.0"
            }
        }
    }



    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.google.firebase:firebase-common")
            }
        }
        val androidUnitTest by getting
        if (supportIosTarget) {
            val iosX64Main by getting
            val iosArm64Main by getting
            val iosSimulatorArm64Main by getting
            val iosMain by creating {
                dependsOn(commonMain)
                iosX64Main.dependsOn(this)
                iosArm64Main.dependsOn(this)
                iosSimulatorArm64Main.dependsOn(this)
            }
            val iosX64Test by getting
            val iosArm64Test by getting
            val iosSimulatorArm64Test by getting
            val iosTest by creating {
                dependsOn(commonTest)
                iosX64Test.dependsOn(this)
                iosArm64Test.dependsOn(this)
                iosSimulatorArm64Test.dependsOn(this)
            }
        }
    }
}

android {
    namespace = "com.estiven.firebase_app"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

signing {
    sign(publishing.publications)
}

if (project.property("firebase-app.skipIosTests") == "true") {
    tasks.forEach {
        if (it.name.contains("ios", true) && it.name.contains("test", true)) {
            it.enabled = false
        }
    }
}