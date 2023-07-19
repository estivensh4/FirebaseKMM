package com.estiven.firebase_auth

import com.estiven.firebase_app.Firebase

actual val Firebase.auth
    get() = FirebaseAuth(com.google.firebase.auth.FirebaseAuth.getInstance())

actual class FirebaseAuth(val android: com.google.firebase.auth.FirebaseAuth) {

}