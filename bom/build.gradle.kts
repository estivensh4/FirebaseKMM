import com.estiven.buildsrc.Configuration

plugins {
    kotlin("jvm")
}



rootProject.ext.apply {
    set("PUBLISH_GROUP_ID", Configuration.artifactGroup)
    set("PUBLISH_ARTIFACT_ID", "firebase-bom")
    set("PUBLISH_VERSION", Configuration.version)
}

dependencies {
    constraints {
        api(project(":firebase-app"))
        api(project(":firebase-auth"))
        api(project(":firebase-firestore"))
        api(project(":firebase-storage"))
    }
}

apply(from ="${rootDir}/scripts/publish-module.gradle")
