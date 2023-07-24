/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:22
 *
 */

package com.estivensh4.firebase_firestore

import com.google.firebase.firestore.AggregateQuery

actual class AggregateQuery(val android: AggregateQuery) {
    actual val query
        get() = Query(android.query)
}