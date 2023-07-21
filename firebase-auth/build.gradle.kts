version = project.property("firebase-auth.version").toString()

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
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
            baseName = "firebase-auth"
        }
        noPodspec()
        pod("FirebaseAuth") {
            version = "10.11.0"
        }
    }

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
                implementation("junit:junit:4.13.2")
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.google.firebase:firebase-auth")
            }
        }
    }
}


android {
    namespace = "com.estiven.firebase_auth"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
        getByName("androidTest") {
            java.srcDir(file("src/androidUnitTest/kotlin"))
            manifest.srcFile("src/androidUnitTest/AndroidManifest.xml")
        }
    }
    packagingOptions {
        resources.pickFirsts.add("META-INF/kotlinx-serialization-core.kotlin_module")
        resources.pickFirsts.add("META-INF/AL2.0")
        resources.pickFirsts.add("META-INF/LGPL2.1")
    }
    lint {
        abortOnError = false
    }
}

signing {
    sign(publishing.publications)
}