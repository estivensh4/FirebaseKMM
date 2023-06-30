package com.estiven.firebase_firestore

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform