package com.estiven.firebase_auth

actual class ActionCodeResult(val android: com.google.firebase.auth.ActionCodeResult) {
    actual val operation get() = android.operation.toLong()
    actual val email get() = android.info?.email
}