package com.estiven.firebase_firestore

actual class DocumentSnapshot(val iOS: NativeDocumentSnapshot) {
    actual fun get(field: String) = iOS.valueForField(field)
    actual fun get(fieldPath: FieldPath) = iOS.valueForField(fieldPath)
    actual fun get(field: String, serverTimestampBehavior: ServerTimestampBehavior) =
        iOS.valueForField(field, serverTimestampBehavior)

    actual fun get(fieldPath: FieldPath, serverTimestampBehavior: ServerTimestampBehavior) =
        iOS.valueForField(fieldPath, serverTimestampBehavior)

    actual inline fun <reified T : Any> toObject() = iOS.data() as T
}