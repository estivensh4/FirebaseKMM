package com.estiven.firebase_auth

expect class FirebaseUserMetadata {
    val creationTimestamp: Long?
    val lastSignInTimestamp: Long?
}