/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:54
 *
 */

package com.estivensh4.firebase_messaging

import cocoapods.FirebaseMessaging.FIRMessaging
import com.estivensh4.firebase_app.Firebase
import com.estivensh4.firebase_common.await
import com.estivensh4.firebase_common.awaitResult

actual val Firebase.messaging
    get() = FirebaseMessaging(FIRMessaging.messaging())

actual class FirebaseMessaging(private val iOS: FIRMessaging) {
    actual val autoInitEnabled get() = iOS.autoInitEnabled
    actual fun setAutoInitEnabled(autoInitEnabled: Boolean) {
        iOS.setAutoInitEnabled(autoInitEnabled)
    }

    /**
     * Get token.
     *
     * @return
     */
    actual suspend fun getToken() = iOS.awaitResult { tokenWithCompletion(it) }

    /**
     * Subscribe to topic.
     *
     * @param topic Topic
     */
    actual suspend fun subscribeToTopic(topic: String) {
        await { iOS.subscribeToTopic(topic, it) }
    }

    /**
     * Unsubscribe from topic.
     *
     * @param topic Topic
     */
    actual suspend fun unsubscribeFromTopic(topic: String) {
        await { iOS.unsubscribeFromTopic(topic, it) }
    }
}