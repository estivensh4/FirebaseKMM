package com.estivensh4.firebase_firestore

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

actual open class Query(private val android: com.google.firebase.firestore.Query) {

    actual fun count() = AggregateQuery(android.count())
    actual fun endAt(vararg fieldValues: Any) = Query(android.endAt(fieldValues))
    actual fun endBefore(vararg fieldValues: Any) =
        Query(android.endBefore(fieldValues))

    actual fun limit(limit: Long) = Query(android.limit(limit))
    actual fun limitToLast(limit: Long) = Query(android.limitToLast(limit))
    actual fun orderBy(field: String) = Query(android.orderBy(field))
    actual fun orderBy(field: FieldPath) = Query(android.orderBy(field.android))
    actual fun orderBy(field: String, direction: Direction) =
        Query(android.orderBy(field, direction))

    actual fun orderBy(field: FieldPath, direction: Direction) =
        Query(android.orderBy(field.android, direction))

    actual fun startAt(vararg fieldValues: Any) = Query(android.startAt(fieldValues))
    actual fun startAt(snapshot: DocumentSnapshot) =
        Query(android.startAt(snapshot.android))

    actual fun startAfter(vararg fieldValues: Any) =
        Query(android.startAfter(fieldValues))

    actual fun startAfter(snapshot: DocumentSnapshot) =
        Query(android.startAfter(snapshot.android))

    actual fun where(filter: Filter) = Query(android.where(filter.android))
    actual fun whereEqualTo(field: String, value: Any?) =
        Query(android.whereEqualTo(field, value))

    actual fun whereEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(android.whereEqualTo(fieldPath.android, value))

    actual fun whereNotEqualTo(field: String, value: Any?) =
        Query(android.whereNotEqualTo(field, value))

    actual fun whereNotEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(android.whereNotEqualTo(fieldPath.android, value))

    actual fun whereArrayContains(field: String, value: Any) =
        Query(android.whereArrayContains(field, value))

    actual fun whereArrayContains(fieldPath: FieldPath, value: Any) =
        Query(android.whereEqualTo(fieldPath.android, value))

    actual fun whereGreaterThan(field: String, value: Any) =
        Query(android.whereGreaterThan(field, value))

    actual fun whereGreaterThan(fieldPath: FieldPath, value: Any) =
        Query(android.whereGreaterThan(fieldPath.android, value))

    actual fun whereLessThan(field: String, value: Any) =
        Query(android.whereLessThan(field, value))

    actual fun whereLessThan(fieldPath: FieldPath, value: Any) =
        Query(android.whereLessThan(fieldPath.android, value))

    actual fun whereIn(field: String, values: MutableList<Any>) =
        Query(android.whereIn(field, values))

    actual fun whereIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereIn(fieldPath.android, values))

    actual fun whereArrayContainsAny(field: String, values: MutableList<Any>) =
        Query(android.whereArrayContainsAny(field, values))

    actual fun whereArrayContainsAny(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereArrayContainsAny(fieldPath.android, values))

    actual fun whereGreaterThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(android.whereGreaterThanOrEqualTo(field, values))

    actual fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereGreaterThanOrEqualTo(fieldPath.android, values))

    actual fun whereLessThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(android.whereLessThanOrEqualTo(field, values))

    actual fun whereLessThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereLessThanOrEqualTo(fieldPath.android, values))

    actual fun whereNotIn(field: String, values: MutableList<Any>) =
        Query(android.whereNotIn(field, values))

    actual fun whereNotIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(android.whereNotIn(fieldPath.android, values))

    actual val snapshotListener
        get() = callbackFlow {
            val snapshotListener = android.addSnapshotListener { value, error ->
                if (error != null) close(error)
                if (value != null) trySend(QuerySnapshot(value))
            }
            awaitClose { snapshotListener.remove() }
        }
}
