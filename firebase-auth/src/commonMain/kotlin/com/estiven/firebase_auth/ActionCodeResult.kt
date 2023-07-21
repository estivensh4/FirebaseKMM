package com.estiven.firebase_auth

expect class ActionCodeResult {
    val operation: Long
    val email: String?
}