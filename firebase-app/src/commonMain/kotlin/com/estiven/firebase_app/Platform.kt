package com.estiven.firebase_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform