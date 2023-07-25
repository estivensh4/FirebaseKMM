# Firebase Auth

[![Maven Central](https://img.shields.io/maven-central/v/io.github.estivensh4/firebase-auth)](https://search.maven.org/search?q=g:io.github.estivensh4)

## Checks current authentication status

1. Declares an instance of FirebaseAuth.

```kotlin
val auth = Firebase.auth
```

2. When you initialize the activity, verify that the user is logged in.

```kotlin
val currentUser = auth.currentUser
if (currentUser != null) {
    // code
}
```

## Register new users

Create a new createAccount method that receives an email address and password, validates them, and
then creates a new user with the createUserWithEmailAndPassword method.

```kotlin
viewModelScope.launch {
    try {
        val authResult = auth.createUserWithEmailAndPassword(
            email = "test@test.com",
            password = "12345678"
        )
        val user = authResult.user
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
}
```

## Existing user access

Create a new signIn method that receives an email address and password, validates them, and then
allows a user to log in with the signInWithEmailAndPassword method.

```kotlin
viewModelScope.launch {
    try {
        val authResult = auth.signInWithEmailAndPassword(
            email = "test@test.com",
            password = "12345678"
        )
        val user = authResult.user
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
}
```

## Access user information

If a user has successfully logged in, you can get their account information at any time using the
getCurrentUser method.

```kotlin
val user = auth.currentUser
user?.let {
    val name = it.displayName
    val email = it.email
    val photoUrl = it.photoUrl
    val emailVerified = it.isEmailVerified
    val uid = it.uid
}
```

## Update a user's profile

You can update basic information of a user's profile (its visible name and the URL of its profile
picture) with the updateProfile method. For example:

```kotlin
viewModelScope.launch {
    try {
        val currentUser = auth.currentUser
        currentUser.updateProfile(
            displayName = "test",
            photoUrl = null
        )
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
}
```

## Deleting a user

To delete a user account, you can use the delete method. For example:

```kotlin
viewModelScope.launch {
    try {
        val currentUser = auth.currentUser
        currentUser?.delete()
    } catch (exception: Exception) {
        exception.printStackTrace()
    }
}
```

To log out of a user's session, call signOut as follows:

```kotlin
 auth.signOut()
```