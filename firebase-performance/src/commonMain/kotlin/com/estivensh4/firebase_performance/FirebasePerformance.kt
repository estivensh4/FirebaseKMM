package com.estivensh4.firebase_performance

import com.estivensh4.firebase_app.Firebase

expect val Firebase.performance: FirebasePerformance

expect class FirebasePerformance {
    val isPerformanceCollectionEnabled: Boolean
    fun newTrace(traceName: String): Trace?
    fun setPerformanceCollectionEnabled(enable: Boolean)
}

expect class Trace {
    val name: String
    val attributes: Map<String, String>
    fun start()
    fun stop()
    fun putMetric(metricName: String, value: Long)
    fun removeAttribute(attribute: String)
    fun incrementMetric(metricName: String, value: Long)
    fun getLongMetric(metricName: String)
}