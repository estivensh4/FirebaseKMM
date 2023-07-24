package com.estivensh4.firebase_auth

expect class ActionCodeResult {
    val operation: Long
    val email: String?
}