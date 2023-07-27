<h1 align="left">FirebaseKMM <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/estivensh4/FirebaseKMM?style=flat-square"></h1>

FirebaseKMM is a Firebase Extension that supports cross-platform projects directly from iOS and Android.

## Available libraries

The following libraries are available for the various Firebase products.

| Product	                                                        |                                                               Version                                                                |                                                                                      Code coverage                                                                                      | Android     | iOS            |
|-----------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|-------------|----------------|
| [Auth](https://firebase.google.com/docs/auth)                   |     <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-auth?versionPrefix=0.7">      |              [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-auth/src/commonMain/kotlin/com/estivensh4/firebase_auth/FirebaseAuth.kt)️              | ✅ available | ✅️ available   |
| [Firestore](https://firebase.google.com/docs/firestore)         |   <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-firestore?versionPrefix=0.7">   |       [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-firestore/src/commonMain/kotlin/com/estivensh4/firebase_firestore/FirebaseFirestore.kt)       | ✅ available | ✅️ available   |
| [Storage](https://firebase.google.com/docs/storage)             |    <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-storage?versionPrefix=0.7">    |          [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-storage/src/commonMain/kotlin/com/estivensh4/firebase_storage/FirebaseStorage.kt)          | ✅ available | ✅️ available   |
| [Config](https://firebase.google.com/docs/remote-config)        |    <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-config?versionPrefix=0.7">     |           [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-config/src/commonMain/kotlin/com/estivensh4/firebase_config/FirebaseConfig.kt)            | ✅ available | ✅️ available   |
| [Crashlytics](https://firebase.google.com/docs/crashlytics)     |  <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-crashlytics?versionPrefix=0.7">  |    [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-crashlytics/src/commonMain/kotlin/com/estivensh4/firebase_crashlytics/FirebaseCrashlytics.kt)    | ✅ available | ✅️ available   |
| [Installations](https://firebase.google.com/docs/installations) | <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-installations?versionPrefix=0.7"> | [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-installations/src/commonMain/kotlin/com/estivensh4/firebase_installations/FirebaseInstallations.kt) | ✅ available | ✅️ available   |
| [Performance](https://firebase.google.com/docs/perf-mon)        |  <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-performance?versionPrefix=0.7">  |    [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-performance/src/commonMain/kotlin/com/estivensh4/firebase_performance/FirebasePerformance.kt)    | ✅ available | ✅️ available   |
| [Messaging](https://firebase.google.com/docs/cloud-messaging)   |   <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-messaging?versionPrefix=0.7">   |       [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-messaging/src/commonMain/kotlin/com/estivensh4/firebase_messaging/FirebaseMessaging.kt)       | ✅ available | ✔️ In progress |

## Implementation 

1. add the dependency below to your **module**'s `build.gradle.kts` file:

```gradle
val commonMain by getting {
   dependencies {
      implementation("io.github.estivensh4:firebase-$module:$version")
    }
}
```

2. adds the configuration file (Required)

|                                                            	 **Android**                                                            |                                                             **iOS**                                                             |
|:-----------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------:|
| <img alt="Maven Central" src="https://github.com/estivensh4/FirebaseKMM/blob/main/documentation/images/android-implementation.png"> | <img alt="Maven Central" src="https://github.com/estivensh4/FirebaseKMM/blob/main/documentation/images/ios-implementation.png"> | 


3. add dependencies in the pod (Required)

```kotlin
cocoapods {
    framework {
        baseName = "shared"
    }

    // add this line for each required module
    pod("Firebase/$module", "~> 10.11.0")
}
```

## Examples
[FirebaseAuth](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-auth)<br>
[FirebaseConfig](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-config)<br>
[FirebaseCrashlytics](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-crashlytics)<br>
[FirebaseFirestore](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-firestore)<br>
[FirebaseInstallations](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-installations)<br>
[FirebaseMessaging](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-messaging)<br>
[FirebasePerformance](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-performance)<br>
[FirebaseStorage](https://github.com/estivensh4/FirebaseKMM/tree/main/firebase-storage)

## Inspiration
This library was mostly inspired by [Firebase Kotlin SDK](https://github.com/GitLiveApp/firebase-kotlin-sdk).<br>

> The Firebase Kotlin SDK is a Kotlin-first SDK for Firebase. It's API is similar to the Firebase Android SDK Kotlin Extensions but also supports multiplatform projects, enabling you to use Firebase directly from your common source targeting iOS, Android or JS.

## Find this repository useful? :heart:

Support it by joining __[estivensh4](https://github.com/estivensh4/FirebaseKMM)__ for this
repository. :star: <br>
Also __[follow](https://github.com/estivensh4)__ me for my next creations! 🤩

## License

```
Copyright 2023 Estiven Sánchez
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```