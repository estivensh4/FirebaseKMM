package com.estivensh4.firebase_installations

import com.estivensh4.firebase_app.Firebase

expect val Firebase.installations: FirebaseInstallations

expect class FirebaseInstallations {
    suspend fun id(): String
    suspend fun delete()
    suspend fun getToken(forceRefresh: Boolean): String
}