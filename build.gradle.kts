/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 24/7/23 00:05
 *
 */

import com.estivensh4.buildsrc.ProjectConfig

repositories {
    google()
    mavenCentral()
}

plugins {
    id("com.android.library").version("8.0.2").apply(false)
    kotlin("multiplatform").version("1.8.21").apply(false)
    kotlin("plugin.serialization").version("1.8.21")
    id("org.jetbrains.kotlinx.kover") version "0.7.2"
    id("org.sonarqube") version "4.2.1.3168"
    id("base")
}

apply(plugin = "org.jetbrains.kotlinx.kover")

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.2")
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:4.2.1.3168")
        /*classpath(libs.google.services.plugin)
        classpath(libs.kover.plugin)
        classpath(libs.sonar.plugin)*/
    }

}

val targetSdkVersion by extra(32)
val minSdkVersion by extra(19)

subprojects {
    if (project.name != "app") {

        group = ProjectConfig.groupId

        apply(plugin = "maven-publish")
        apply(plugin = "signing")

        repositories {
            mavenLocal()
            google()
            mavenCentral()
        }

        afterEvaluate {
            dependencies {
                "commonMainImplementation"(libs.kotlin.coroutines.core)
                "commonMainImplementation"(libs.eygraber.uri)
                "androidMainImplementation"(libs.kotlin.coroutines.playservices)
                "androidMainImplementation"(platform(libs.firebase.bom))
                "commonTestImplementation"(libs.kotlin.coroutines.core)
                "commonTestImplementation"(libs.kotlin.coroutines.test)
                "commonTestImplementation"(kotlin("test-common"))
                "commonTestImplementation"(kotlin("test-annotations-common"))
                "androidUnitTestImplementation"(kotlin("test-junit"))
                "androidUnitTestImplementation"(libs.junit4)
                "androidUnitTestImplementation"(libs.androidx.test.core)
                "androidUnitTestImplementation"(libs.androidx.test.ext)
                "androidUnitTestImplementation"(libs.androidx.test.runner)
                "androidUnitTestImplementation"(libs.roboelectric)
            }
        }

        tasks.withType<Sign>().configureEach {
            onlyIf { !project.gradle.startParameter.taskNames.contains("publishToMavenLocal") }
        }

        configure<PublishingExtension> {
            repositories {
                maven {
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username = project.findProperty("ossrhUsername") as String? ?: System.getenv(
                            "ossrhUsername"
                        )
                        password = project.findProperty("ossrhPassword") as String? ?: System.getenv(
                            "ossrhPassword"
                        )
                    }
                }

                publications.all {
                    this as MavenPublication
                    pom {
                        name.set("FirebaseKMM")
                        description.set("FirebaseKMM is a Firebase Extension that supports cross-platform projects also based on Firebase Kotlin SDK, allowing you to directly from iOS and Android.")
                        url.set("https://github.com/estivensh4/FirebaseKMM")
                        inceptionYear.set("2023")

                        scm {
                            url.set("https://github.com/estivensh4/FirebaseKMM")
                            connection.set("scm:git:https://github.com/estivensh4/FirebaseKMM")
                            developerConnection.set("scm:git:https://github.com/estivensh4/FirebaseKMM")
                            tag.set("HEAD")
                        }

                        issueManagement {
                            system.set("GitHub Issues")
                            url.set("https://github.com/estivensh4/FirebaseKMM/issues")
                        }

                        developers {
                            developer {
                                name.set("Estiven Sánchez")
                                email.set("estivensh4@gmail.com")
                            }
                        }

                        licenses {
                            license {
                                name.set("The Apache Software License, Version 2.0")
                                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                distribution.set("repo")
                            }
                        }
                    }
                }
            }
        }
    }
}

sonarqube {

    properties {
        property("sonar.projectName", "FirebaseKMM")
        property("sonar.projectKey", "firebase-kmm")
        property("sonar.sourceEncoding", "UTF-8")
        //property("sonar.exclusions", excludes)
        property("sonar.login", "admin")
        property("sonar.password", "admin")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "$buildDir/reports/kover/report.xml"
        )
    }
}

koverReport {
    // filters for all report types of all build variants
    defaults {
        /*mergeWith("firebase-app")
        mergeWith("firebase-auth")
        mergeWith("firebase-config")
        mergeWith("firebase-crashlytics")
        mergeWith("firebase-firestore")
        mergeWith("firebase-installations")
        mergeWith("firebase-messaging")
        mergeWith("firebase-performance")
        mergeWith("firebase-storage")*/
    }
    filters {
        excludes {
            classes(
                "*Fragment",
                "*Fragment\$*",
                "*Activity",
                "*Activity\$*",
                "*.databinding.*",
                "*.BuildConfig"
            )
        }
    }

    /*    androidReports("release") {
            // filters for all report types only of 'release' build type
            filters {
                excludes {
                    classes(
                        "*Fragment",
                        "*Fragment\$*",
                        "*Activity",
                        "*Activity\$*",
                        "*.databinding.*",
                        "*.BuildConfig",

                        // excludes debug classes
                        "*.DebugUtil"
                    )
                }
            }
        }*/

}