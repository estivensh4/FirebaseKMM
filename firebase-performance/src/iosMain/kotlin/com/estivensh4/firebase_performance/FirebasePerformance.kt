/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 20:58
 *
 */

package com.estivensh4.firebase_performance

import cocoapods.FirebasePerformance.FIRPerformance
import com.estivensh4.firebase_app.Firebase

actual val Firebase.performance
    get() = FirebasePerformance(FIRPerformance())

actual class FirebasePerformance(private val iOS: FIRPerformance) {
    actual val isPerformanceCollectionEnabled get() = iOS.dataCollectionEnabled

    /**
     * New trace.
     *
     * @param traceName Trace name
     * @return
     */
    actual fun newTrace(traceName: String) = iOS.traceWithName(traceName)?.let { Trace(it) }

    /**
     * Set performance collection enabled.
     *
     * @param enable Enable
     */
    actual fun setPerformanceCollectionEnabled(enable: Boolean) {
        iOS.setDataCollectionEnabled(enable)
    }
}