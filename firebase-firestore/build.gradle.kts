version = project.property("firebase-firestore.version") as String
plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlinx.kover")
}

kotlin {
    android {
        publishAllLibraryVariants()
    }
    val supportIosTarget = project.property("skipIosTarget") != "true"
    if(supportIosTarget){
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
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":firebase-app"))
                //implementation(project(":firebase-common"))
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
        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation("androidx.test:core:1.4.0")
                implementation("androidx.test.ext:junit:1.1.3")
                implementation("androidx.test:runner:1.4.0")
            }
        }
        if (supportIosTarget){
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
    namespace = "com.estiven.firebase_firestore"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        abortOnError = false
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
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