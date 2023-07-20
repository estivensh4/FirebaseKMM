package com.estiven.firebase_auth

expect class AdditionalUserInfo {
    val providerId: String?
    val username: String?
    val profile: Map<String?, Any>?
    val isNewUser: Boolean
}