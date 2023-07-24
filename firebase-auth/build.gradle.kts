import com.estivensh4.buildsrc.Module
import com.estivensh4.buildsrc.ProjectConfig

version = project.property("firebase-auth.version").toString()

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
            baseName = ProjectConfig.iOS.authBaseName
        }
        noPodspec()
        pod(ProjectConfig.iOS.authPod) {
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
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.firebase.auth)
            }
        }
    }
}


android {
    namespace = ProjectConfig.Android.authModule
    compileSdk = ProjectConfig.Android.compileSdk
    defaultConfig {
        minSdk = ProjectConfig.Android.minSdk
    }
}

signing {
    sign(publishing.publications)
}