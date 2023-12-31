/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_performance

import android.annotation.SuppressLint

actual class Trace(private val android: com.google.firebase.perf.metrics.Trace) {
    actual val name
        @SuppressLint("VisibleForTests")
        get() = android.name
    actual val attributes get() = android.attributes.toMap()

    /**
     * Start.
     */
    actual fun start() {
        android.start()
    }

    /**
     * Stop.
     */
    actual fun stop() {
        android.stop()
    }

    /**
     * Put metric.
     *
     * @param metricName Metric name
     * @param value Value
     */
    actual fun putMetric(metricName: String, value: Long) {
        android.putMetric(metricName, value)
    }

    /**
     * Remove attribute.
     *
     * @param attribute Attribute
     */
    actual fun removeAttribute(attribute: String) {
        android.removeAttribute(attribute)
    }

    /**
     * Increment metric.
     *
     * @param metricName Metric name
     * @param value Value
     */
    actual fun incrementMetric(metricName: String, value: Long) {
        android.incrementMetric(metricName, value)
    }

    /**
     * Get long metric.
     *
     * @param metricName Metric name
     */
    actual fun getLongMetric(metricName: String) {
        android.getLongMetric(metricName)
    }
}