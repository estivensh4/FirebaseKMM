/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_performance

import cocoapods.FirebasePerformance.FIRTrace

actual class Trace(private val iOS: FIRTrace) {
    actual val name get() = iOS.name

    @Suppress("UNCHECKED_CAST")
    actual val attributes get() = iOS.attributes as Map<String, String>

    /**
     * Start.
     */
    actual fun start() {
        iOS.start()
    }

    /**
     * Stop.
     */
    actual fun stop() {
        iOS.stop()
    }

    /**
     * Put metric.
     *
     * @param metricName Metric name
     * @param value Value
     */
    actual fun putMetric(metricName: String, value: Long) {
        iOS.setIntValue(value, metricName)
    }

    /**
     * Remove attribute.
     *
     * @param attribute Attribute
     */
    actual fun removeAttribute(attribute: String) {
        iOS.removeAttribute(attribute)
    }

    /**
     * Increment metric.
     *
     * @param metricName Metric name
     * @param value Value
     */
    actual fun incrementMetric(metricName: String, value: Long) {
        iOS.incrementMetric(metricName, value)
    }

    /**
     * Get long metric.
     *
     * @param metricName Metric name
     */
    actual fun getLongMetric(metricName: String) {
        iOS.valueForIntMetric(metricName)
    }
}