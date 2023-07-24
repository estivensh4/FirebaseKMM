package com.estivensh4.firebase_performance

import com.estivensh4.firebase_app.Firebase

actual val Firebase.performance
    get() = FirebasePerformance(com.google.firebase.perf.FirebasePerformance.getInstance())

actual class FirebasePerformance(private val android: com.google.firebase.perf.FirebasePerformance) {
    actual val isPerformanceCollectionEnabled get() = android.isPerformanceCollectionEnabled

    /**
     * New trace.
     *
     * @param traceName Trace name
     * @return [Trace] or null
     */
    actual fun newTrace(traceName: String): Trace? = Trace(android.newTrace(traceName))

    /**
     * Set performance collection enabled.
     *
     * @param enable Enable
     */
    actual fun setPerformanceCollectionEnabled(enable: Boolean) {
        android.isPerformanceCollectionEnabled = enable
    }
}