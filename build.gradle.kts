import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
    kotlin("plugin.serialization").version("1.8.10")
    id("org.jetbrains.kotlinx.kover") version "0.7.2"
    id("org.sonarqube") version "4.2.1.3168"
}

apply(plugin = "org.jetbrains.kotlinx.kover")

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
        classpath("org.jetbrains.kotlinx:kover-gradle-plugin:0.7.2")
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:4.2.1.3168")
    }
}

subprojects {
    if (project.name != "app"){
        afterEvaluate {
            dependencies {
                "commonMainImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                "commonMainImplementation"("org.jetbrains.kotlin:kotlin-reflect:1.8.22")
                "commonMainImplementation"("com.google.code.gson:gson:2.10.1")
                "commonMainImplementation"("com.eygraber:uri-kmp:0.0.3")
                "androidMainImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.1")
                "androidMainImplementation"(platform("com.google.firebase:firebase-bom:32.1.1"))
                "commonTestImplementation"(kotlin("test-common"))
                "commonTestImplementation"(kotlin("test-annotations-common"))
                "commonTestImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                "commonTestImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
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

tasks.sonarqube.dependsOn(":clean")
tasks.sonarqube.dependsOn(":koverXmlReport")