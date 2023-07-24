/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:25
 *
 */

package com.estivensh4.firebase_firestore

import cocoapods.FirebaseFirestore.FIRAggregateQuery

actual class AggregateQuery(private val iOS: FIRAggregateQuery) {
    actual val query
        get() = Query(iOS.query)
}