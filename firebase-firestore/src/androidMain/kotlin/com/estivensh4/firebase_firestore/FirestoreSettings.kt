/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.jetbrains.annotations.Contract

actual class FirestoreSettings(val android: FirebaseFirestoreSettings) {
    actual val host
        get() = android.host
    actual val isSslEnabled
        get() = android.isSslEnabled

    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val cacheSizeBytes
        get() = android.cacheSizeBytes

    @Deprecated(
        message = "Deprecated Instead, use cacheSettings to check cache size.",
        replaceWith = ReplaceWith("cacheSettings")
    )
    @get:Contract(pure = true)
    actual val isPersistenceEnabled
        get() = android.isPersistenceEnabled
}