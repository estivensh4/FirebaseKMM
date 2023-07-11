package com.estiven.firebase_firestore

import cocoapods.FirebaseFirestore.FIRQuery
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

actual open class Query(private val iOS: FIRQuery) {

    actual val snapshotListener
        get() = callbackFlow {
            val listener = iOS.addSnapshotListener { firQuerySnapshot, nsError ->
                if (nsError != null) close(Exception(nsError.toString()))
                if (firQuerySnapshot != null) trySend(QuerySnapshot(firQuerySnapshot))
            }
            awaitClose { listener.remove() }
        }

    actual fun count() = AggregateQuery(iOS.count())
    actual fun endAt(vararg fieldValues: Any) =
        Query(iOS.queryEndingAtValues(fieldValues.toList()))

    actual fun endBefore(vararg fieldValues: Any) =
        Query(iOS.queryEndingBeforeValues(fieldValues.toList()))

    actual fun limit(limit: Long) = Query(iOS.queryLimitedTo(limit))
    actual fun limitToLast(limit: Long) = Query(iOS.queryLimitedToLast(limit))
    actual fun orderBy(field: String) = Query(iOS.queryOrderedByField(field))
    actual fun orderBy(field: FieldPath) = Query(iOS.queryOrderedByFieldPath(field.iOS))
    actual fun orderBy(field: String, direction: Direction) =
        Query(iOS.queryOrderedByField(field, direction == Direction.DESCENDING))

    actual fun orderBy(field: FieldPath, direction: Direction) =
        Query(iOS.queryOrderedByFieldPath(field.iOS, direction == Direction.DESCENDING))

    actual fun startAt(vararg fieldValues: Any) =
        Query(iOS.queryStartingAtValues(fieldValues.toList()))

    actual fun startAt(snapshot: DocumentSnapshot) =
        Query(iOS.queryStartingAtDocument(snapshot.iOS))

    actual fun startAfter(vararg fieldValues: Any) =
        Query(iOS.queryStartingAfterValues(fieldValues.toList()))

    actual fun startAfter(snapshot: DocumentSnapshot) =
        Query(iOS.queryStartingAtDocument(snapshot.iOS))

    actual fun where(filter: Filter) = Query(iOS.queryWhereFilter(filter.iOS))
    actual fun whereEqualTo(field: String, value: Any?) =
        Query(iOS.queryWhereField(field = field, isEqualTo = value!!))

    actual fun whereEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isEqualTo = value!!))

    actual fun whereNotEqualTo(field: String, value: Any?) =
        Query(iOS.queryWhereField(field = field, isNotEqualTo = value!!))

    actual fun whereNotEqualTo(fieldPath: FieldPath, value: Any?) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isNotEqualTo = value!!))

    actual fun whereArrayContains(field: String, value: Any) =
        Query(iOS.queryWhereField(field = field, arrayContains = value))

    actual fun whereArrayContains(fieldPath: FieldPath, value: Any) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, arrayContains = value))

    actual fun whereGreaterThan(field: String, value: Any) =
        Query(iOS.queryWhereField(field = field, isGreaterThan = value))

    actual fun whereGreaterThan(fieldPath: FieldPath, value: Any) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isGreaterThan = value))

    actual fun whereLessThan(field: String, value: Any) =
        Query(iOS.queryWhereField(field = field, isLessThan = value))

    actual fun whereLessThan(fieldPath: FieldPath, value: Any) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isLessThan = value))

    actual fun whereIn(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, `in` = values))

    actual fun whereIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, `in` = values))

    actual fun whereArrayContainsAny(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, arrayContains = values))

    actual fun whereArrayContainsAny(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, arrayContains = values))

    actual fun whereGreaterThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, isGreaterThanOrEqualTo = values))

    actual fun whereGreaterThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isGreaterThanOrEqualTo = values))

    actual fun whereLessThanOrEqualTo(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field, isLessThanOrEqualTo = values))

    actual fun whereLessThanOrEqualTo(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, isLessThanOrEqualTo = values))

    actual fun whereNotIn(field: String, values: MutableList<Any>) =
        Query(iOS.queryWhereField(field = field, notIn = values))

    actual fun whereNotIn(fieldPath: FieldPath, values: MutableList<Any>) =
        Query(iOS.queryWhereFieldPath(path = fieldPath.iOS, notIn = values))
}