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
            ios.deploymentTarget = "14.1"
            framework {
                baseName = "firebase-installations"
            }
            noPodspec()
            pod("FirebaseInstallations") {
                version = "10.11.0"
            }
        }

        sourceSets {
            val commonMain by getting {
                dependencies {
                    implementation(project(":firebase-app"))
                }
            }
            val commonTest by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            val androidMain by getting {
                dependencies {
                    api("com.google.firebase:firebase-installations")
                }
            }
        }
    }
}

android {
    namespace = "com.estiven.firebase_installations"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

signing {
    sign(publishing.publications)
}