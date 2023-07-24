/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import com.google.firebase.FirebaseOptions

actual class FirestoreOptions(val android: FirebaseOptions) {
    actual val applicationId
        get() = android.applicationId
    actual val databaseUrl
        get() = android.databaseUrl
    actual val gaTrackingId
        get() = android.gaTrackingId
    actual val gcmSenderId
        get() = android.gcmSenderId ?: ""
    actual val storageBucket
        get() = android.storageBucket
    actual val projectId
        get() = android.projectId
}