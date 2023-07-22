plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.kotlinx.kover")
}

group = "io.github.estivensh4"
version = "0.5.1"

repositories {
    google()
    mavenCentral()
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
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
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
    }
}

android {
    namespace = "com.estiven.firebase_app"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

/*signing {
    sign(publishing.publications)
}*/

if (project.property("firebase-app.skipIosTests") == "true") {
    tasks.forEach {
        if (it.name.contains("ios", true) && it.name.contains("test", true)) {
            it.enabled = false
        }
    }
}

rootProject.ext.apply {
    set("PUBLISH_GROUP_ID", com.estiven.buildsrc.Configuration.artifactGroup)
    set("PUBLISH_ARTIFACT_ID", "firebase-app")
    set("PUBLISH_VERSION", com.estiven.buildsrc.Configuration.version)
}

apply(from = "${rootDir}/scripts/publish-module.gradle")