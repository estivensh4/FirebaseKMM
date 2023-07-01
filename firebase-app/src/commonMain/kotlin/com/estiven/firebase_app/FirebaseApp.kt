@file:JvmMultifileClass
@file:JvmName("CommonKt")

package com.estiven.firebase_app

import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

object Firebase

expect class FirebaseApp {
    val name: String
    val options: FirebaseOptions
    fun delete()
}

expect val Firebase.app: FirebaseApp

expect fun Firebase.initialize(context: Any? = null): FirebaseApp?

expect fun Firebase.initialize(context: Any? = null, options: FirebaseOptions): FirebaseApp?

expect fun Firebase.initialize(context: Any? = null, options: FirebaseOptions, name: String): FirebaseApp?

val Firebase.options: FirebaseOptions
    get() = Firebase.app.options

data class FirebaseOptions(
    val applicationId: String,
    val apiKey: String,
    val databaseUrl: String? = null,
    val gaTrackingId: String? = null,
    val storageBucket: String? = null,
    val projectId: String? = null,
    val gcmSenderId: String? = null,
    val authDomain: String? = null
)