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
    id("com.github.ben-manes.versions") version "0.47.0"

}

tasks {
    val updateVersions by registering {
        dependsOn(
            "firebase-app:updateVersion", "firebase-app:updateDependencyVersion",
        )
    }
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
        classpath("com.adarshr:gradle-test-logger-plugin:3.2.0")
        classpath("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.2")
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:4.2.1.3168")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.7")
    }

}

val targetSdkVersion by extra(32)
val minSdkVersion by extra(19)

subprojects {
    if (project.name != "app") {
        group = "io.github.estivensh4"
        apply(plugin = "com.adarshr.test-logger")

        tasks.withType<Test> {
            testLogging {
                showExceptions = true
                exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
                showStandardStreams = true
                showCauses = true
                showStackTraces = true
                events = setOf(
                    org.gradle.api.tasks.testing.logging.TestLogEvent.STARTED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT,
                    org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
                )
            }
        }
        afterEvaluate {
            dependencies {
                "commonMainImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                "androidMainImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.1")
                "androidMainImplementation"(platform("com.google.firebase:firebase-bom:32.1.1"))
                "commonTestImplementation"(kotlin("test-common"))
                "commonTestImplementation"(kotlin("test-annotations-common"))
                "commonTestImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                "commonTestImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
                "androidUnitTestImplementation"(kotlin("test-junit"))
                "androidUnitTestImplementation"("junit:junit:4.13.2")
                "androidUnitTestImplementation"("androidx.test:core:1.5.0")
                "androidUnitTestImplementation"("androidx.test.ext:junit:1.1.5")
                "androidUnitTestImplementation"("androidx.test:runner:1.5.2")
                "androidUnitTestImplementation"("androidx.test:rules:1.5.0")
                "androidUnitTestImplementation"("io.mockk:mockk:1.13.5")
                "androidUnitTestImplementation"("org.robolectric:robolectric:4.9.2")
                "commonMainImplementation"("com.eygraber:uri-kmp:0.0.3")
            }
        }

        repositories {
            mavenLocal()
            google()
            mavenCentral()
        }
        apply(plugin = "maven-publish")
        apply(plugin = "signing")

        tasks.withType<Sign>().configureEach {
            onlyIf { !project.gradle.startParameter.taskNames.contains("publishToMavenLocal") }
        }

        configure<PublishingExtension> {
            repositories {
                maven {
                    url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                    credentials {
                        username =
                            project.findProperty("sonatypeUsername") as String? ?: System.getenv(
                                "sonatypeUsername"
                            )
                        password =
                            project.findProperty("sonatypePassword") as String? ?: System.getenv(
                                "sonatypePassword"
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
                                comments.set("A business-friendly OSS license")
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

/*koverReport {
    defaults {
        html{

        }
    }
}*/
/*koverMerged {
    enable()
    xmlReport {
        onCheck.set(true)
    }
    htmlReport {
        onCheck.set(true)
    }
    verify {
        onCheck.set(true)
    }
}*/

koverMerged {
    //this.ext.

}

koverReport {
    // filters for all report types of all build variants
    defaults {
       // mergeWith("firebase-firestore")
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

/*
tasks.withType<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask> {

    fun isNonStable(version: String): Boolean {
        val stableKeyword =
            listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val versionMatch = "^[0-9,.v-]+(-r)?$".toRegex().matches(version)

        return (stableKeyword || versionMatch).not()
    }

    rejectVersionIf {
        isNonStable(candidate.version)
    }

    checkForGradleUpdate = true
    outputFormatter = "plain,html"
    outputDir = "build/dependency-reports"
    reportfileName = "dependency-updates"
}
*/

