/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

@file:JvmMultifileClass
@file:JvmName("CommonKt")

package com.estivensh4.firebase_app

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

expect fun Firebase.initialize(
    context: Any? = null,
    options: FirebaseOptions,
    name: String
): FirebaseApp?

val Firebase.options: FirebaseOptions
    get() = Firebase.app.options

expect fun Firebase.apps(context: Any? = null): List<FirebaseApp>

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