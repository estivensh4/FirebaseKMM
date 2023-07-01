plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("7.4.2").apply(false)
    kotlin("multiplatform").version("1.8.10").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}

subprojects {
    if (project.name != "app"){
        afterEvaluate {
            dependencies {
                "commonMainImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                "commonMainImplementation"("com.eygraber:uri-kmp:0.0.3")
                "androidMainImplementation"("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.1")
                "androidMainImplementation"(platform("com.google.firebase:firebase-bom:32.1.1"))
            }
        }
    }
}