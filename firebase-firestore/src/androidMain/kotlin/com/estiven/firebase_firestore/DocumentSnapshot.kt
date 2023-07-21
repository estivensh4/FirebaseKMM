package com.estiven.firebase_firestore

actual class DocumentSnapshot(val android: NativeDocumentSnapshot) {
    actual fun get(field: String) = android.get(field)
    actual fun get(fieldPath: FieldPath) = android.get(fieldPath.android)
    actual fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior) =
        android.get(field, serverTimestampBehavior)

    actual fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior) =
        android.get(fieldPath.android, serverTimestampBehavior)

    actual inline fun <reified T : Any> toObject() = android.toObject(T::class.java)
}