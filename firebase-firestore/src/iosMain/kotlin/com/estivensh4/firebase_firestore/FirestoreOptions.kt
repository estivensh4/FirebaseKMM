/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:04
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseCore.FIROptions

actual class FirestoreOptions(private val iOS: FIROptions) {
    actual val applicationId
        get() = iOS.googleAppID
    actual val databaseUrl
        get() = iOS.databaseURL
    actual val gaTrackingId
        get() = iOS.trackingID
    actual val gcmSenderId
        get() = iOS.GCMSenderID
    actual val storageBucket
        get() = iOS.storageBucket
    actual val projectId
        get() = iOS.projectID
}