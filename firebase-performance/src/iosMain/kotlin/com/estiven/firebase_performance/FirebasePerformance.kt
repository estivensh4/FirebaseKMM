package com.estiven.firebase_performance

import cocoapods.FirebasePerformance.FIRPerformance
import com.estiven.firebase_app.Firebase

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