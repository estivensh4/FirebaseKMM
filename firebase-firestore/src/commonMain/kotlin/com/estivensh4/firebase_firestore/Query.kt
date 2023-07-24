/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_firestore

import kotlinx.coroutines.flow.Flow

expect open class Query {
    val snapshotListener: Flow<QuerySnapshot>
    fun count(): AggregateQuery
    fun endAt(vararg fieldValues: Any): Query
    fun endBefore(vararg fieldValues: Any): Query
    fun limit(limit: Long): Query
    fun limitToLast(limit: Long): Query
    fun orderBy(field: String): Query
    fun orderBy(field: FieldPath): Query
    fun orderBy(field: String, direction: Direction): Query
    fun orderBy(field: FieldPath, direction: Direction): Query
    fun startAt(vararg fieldValues: Any): Query
    fun startAt(snapshot: DocumentSnapshot): Query
    fun startAfter(vararg fieldValues: Any): Query
    fun startAfter(snapshot: DocumentSnapshot): Query
    fun where(filter: Filter): Query
    fun whereEqualTo(field: String, value: Any?): Query
    fun whereEqualTo(fieldPath: FieldPath, value: Any?): Query
    fun whereNotEqualTo(field: String, value: Any?): Query
    fun whereNotEqualTo(fieldPath: FieldPath, value: Any?): Query
    fun whereArrayContains(field: String, value: Any): Query
    fun whereArrayContains(fieldPath: FieldPath, value: Any): Query
    fun whereGreaterThan(field: String, value: Any): Query
    fun whereGreaterThan(fieldPath: FieldPath, value: Any): Query
    fun whereLessThan(field: String, value: Any): Query
    fun whereLessThan(fieldPath: FieldPath, value: Any): Query
    fun whereIn(field: String, values: MutableList<Any>): Query
    fun whereIn(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereArrayContainsAny(field: String, values: MutableList<Any>): Query
    fun whereArrayContainsAny(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereGreaterThanOrEqualTo(field: String, values: MutableList<Any>): Query
    fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereLessThanOrEqualTo(field: String, values: MutableList<Any>): Query
    fun whereLessThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>): Query
    fun whereNotIn(field: String, values: MutableList<Any>): Query
    fun whereNotIn(fieldPath: FieldPath, values: MutableList<Any>): Query
}