<h1 align="left">Firebase Kotlin SDK <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/estivensh4/FirebaseKMM?style=flat-square"> <a href="https://git.live"><img src="https://img.shields.io/badge/collaborate-on%20gitlive-blueviolet?style=flat-square"></a></h1>
<img align="left" width="75px" src="https://avatars2.githubusercontent.com/u/42865805?s=200&v=4"> 
  <b>Built and maintained with 🧡 by <a href="https://git.live">GitLive</a></b><br/>
  <i>Real-time code collaboration inside any IDE</i><br/>
<br/>
<br/>
The Firebase Kotlin SDK is a Kotlin-first SDK for Firebase. It's API is similar to the <a href="https://firebase.github.io/firebase-android-sdk/reference/kotlin/firebase-ktx/">Firebase Android SDK Kotlin Extensions</a> but also supports multiplatform projects, enabling you to use Firebase directly from your common source targeting <strong>iOS</strong>, <strong>Android</strong> or <strong>JS</strong>.

## Available libraries

The following libraries are available for the various Firebase products.

| Service or Product	                                                             | Gradle Dependency                                                                                                            | API Coverage                                                                                                                                                             |
|---------------------------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Cloud Storage](https://firebase.google.com/docs/storage)                       | [`com.estiven:firebase-storage:1.0.0`](https://search.maven.org/artifact/com.estiven/firebase-storage/1.0.0/pom)             | [![100%](https://img.shields.io/badge/-100%25-green?style=flat-square)](/firebase-storage/src/commonMain/kotlin/com/estiven/firebase_storage/FirebaseStorage.kt)         |
| [Realtime Database](https://firebase.google.com/docs/database)                  | [`dev.gitlive:firebase-database:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-database/1.8.0/pom)           | [![70%](https://img.shields.io/badge/-70%25-orange?style=flat-square)](/firebase-database/src/commonMain/kotlin/dev/gitlive/firebase/database/database.kt)               |
| [Cloud Firestore](https://firebase.google.com/docs/firestore)                   | [`dev.gitlive:firebase-firestore:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-firestore/1.8.0/pom)         | [![60%](https://img.shields.io/badge/-60%25-orange?style=flat-square)](/firebase-firestore/src/commonMain/kotlin/dev/gitlive/firebase/firestore/firestore.kt)            |
| [Cloud Functions](https://firebase.google.com/docs/functions)                   | [`dev.gitlive:firebase-functions:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-functions/1.8.0/pom)         | [![80%](https://img.shields.io/badge/-80%25-green?style=flat-square)](/firebase-functions/src/commonMain/kotlin/dev/gitlive/firebase/functions/functions.kt)             |
| [Cloud Messaging](https://firebase.google.com/docs/cloud-messaging)             | [`dev.gitlive:firebase-messaging:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-messaging/1.8.0/pom)         | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)                                                                                                    |
| [Cloud Storage](https://firebase.google.com/docs/storage)                       | [`dev.gitlive:firebase-storage:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-storage/1.8.0/pom)             | ![0%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)                                                                                                    |
| [Installations](https://firebase.google.com/docs/projects/manage-installations) | [`dev.gitlive:firebase-installations:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-installations/1.8.0/pom) | [![90%](https://img.shields.io/badge/-90%25-green?style=flat-square)](/firebase-installations/src/commonMain/kotlin/dev/gitlive/firebase/installations/installations.kt) |
| [Remote Config](https://firebase.google.com/docs/remote-config)                 | [`dev.gitlive:firebase-config:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-config/1.8.0/pom)               | ![20%](https://img.shields.io/badge/-20%25-orange?style=flat-square)                                                                                                     |
| [Performance](https://firebase.google.com/docs/perf-mon)                        | [`dev.gitlive:firebase-perf:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-perf/1.8.0/pom)                   | ![100%](https://img.shields.io/badge/-1%25-orange?style=flat-square)                                                                                                     |
| [Crashlytics](https://firebase.google.com/docs/crashlytics)                     | [`dev.gitlive:firebase-crashlytics:1.8.0`](https://search.maven.org/artifact/dev.gitlive/firebase-crashlytics/1.8.0/pom)     | ![80%](https://img.shields.io/badge/-1%25-orange?style=flat-square)                                                                                                      |






