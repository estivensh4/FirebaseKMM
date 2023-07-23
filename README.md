<h1 align="left">FirebaseKMM <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/estivensh4/FirebaseKMM?style=flat-square"></h1>

FirebaseKMM is a Firebase Extension that supports cross-platform projects directly from iOS and Android.

## Available libraries

The following libraries are available for the various Firebase products.

| Product	                                                    | Dependency                                                                                                                         | Code coverage                                                                                                                                                                  | Android       | iOS           |
|-------------------------------------------------------------|:-----------------------------------------------------------------------------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|---------------|
| [Auth](https://firebase.google.com/docs/auth)               | <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-auth?versionPrefix=0.5">        | [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-auth/src/commonMain/kotlin/com/estiven/firebase_auth/FirebaseAuth.kt)                      | ✔️In progress | ✔️In progress |
| [Firestore](https://firebase.google.com/docs/firestore)     | <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-firestore?versionPrefix=0.5">   | [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-firestore/src/commonMain/kotlin/com/estiven/firebase_firestore/FirebaseFirestore.kt)       | ✔️In progress | ✔️In progress |
| [Storage](https://firebase.google.com/docs/storage)         | <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-storage?versionPrefix=0.5">     | [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-storage/src/commonMain/kotlin/com/estiven/firebase_storage/FirebaseStorage.kt)             | ✔️In progress | ✔️In progress |
| [Config](https://firebase.google.com/docs/remote-config)    | <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-config?versionPrefix=0.1">      | [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-config/src/commonMain/kotlin/com/estiven/firebase_config/FirebaseConfig.kt)                | ✔️In progress | ✔️In progress |
| [Crashlytics](https://firebase.google.com/docs/crashlytics) | <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-crashlytics?versionPrefix=0.1"> | [![100%](https://img.shields.io/badge/-0%25-lightgrey?style=flat-square)](/firebase-crashlytics/src/commonMain/kotlin/com/estiven/firebase_crashlytics/FirebaseCrashlytics.kt) | ✔️In progress | ✔️In progress |

## Implementation

1. add the dependency below to your **module**'s `build.gradle.kts` file:

```gradle
dependencies {
    implementation("io.github.estivensh4:firebase-$module:$version")
} 
```

2. adds the configuration file

|                                                            	 **Android**                                                            |                                                             **iOS**                                                             |
|:-----------------------------------------------------------------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------:|
| <img alt="Maven Central" src="https://github.com/estivensh4/FirebaseKMM/blob/main/documentation/images/android-implementation.png"> | <img alt="Maven Central" src="https://github.com/estivensh4/FirebaseKMM/blob/main/documentation/images/ios-implementation.png"> | 

## Examples
### FirebaseAuth


## Inspiration
This library was mostly inspired by [Firebase Kotlin SDK](https://github.com/GitLiveApp/firebase-kotlin-sdk).<br>

> The Firebase Kotlin SDK is a Kotlin-first SDK for Firebase. It's API is similar to the Firebase Android SDK Kotlin Extensions but also supports multiplatform projects, enabling you to use Firebase directly from your common source targeting iOS, Android or JS.

## Find this repository useful? :heart:
Support it by joining __[estivensh4](https://github.com/estivensh4/FirebaseKMM)__ for this repository. :star: <br>
Also __[follow](https://github.com/estivensh4)__ me for my next creations! 🤩

# License
```xml
Designed and developed by 2023 Estiven Sánchez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```