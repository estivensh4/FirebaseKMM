package com.estiven.firebase_installations

import com.estiven.firebase_app.Firebase

expect val Firebase.installations: FirebaseInstallations

expect class FirebaseInstallations {
    suspend fun id(): String
    suspend fun delete()
    suspend fun getToken(forceRefresh: Boolean): String
}