import com.estiven.buildsrc.Module
import com.estiven.buildsrc.ProjectConfig

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = project.property("firebase-performance.version") as String

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
            ios.deploymentTarget = ProjectConfig.iOS.deploymentTarget
            framework {
                baseName = ProjectConfig.iOS.performanceBaseName
            }
            noPodspec()
            pod(ProjectConfig.iOS.performancePod) {
                version = ProjectConfig.iOS.firebaseVersion
            }
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
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.google.firebase:firebase-perf")
            }
        }
    }
}

android {
    namespace = ProjectConfig.Android.performanceModule
    compileSdk = ProjectConfig.Android.compileSdk
    defaultConfig {
        minSdk = ProjectConfig.Android.minSdk
    }
}

signing {
    sign(publishing.publications)
}