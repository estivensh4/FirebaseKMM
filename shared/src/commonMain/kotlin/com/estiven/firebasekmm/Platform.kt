package com.estiven.firebasekmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform