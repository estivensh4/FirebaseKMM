package com.estiven.firebase_auth

import cocoapods.FirebaseAuth.FIRAuth
import com.estiven.firebase_app.Firebase

actual val Firebase.auth
    get() = FirebaseAuth(FIRAuth())

actual class FirebaseAuth(val iOS: FIRAuth) {

}