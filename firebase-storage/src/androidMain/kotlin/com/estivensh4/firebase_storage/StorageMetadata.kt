package com.estivensh4.firebase_storage

actual class StorageMetadata(internal val android: com.google.firebase.storage.StorageMetadata) {
    actual val reference get() = android.reference?.let { StorageReference(it) }
    actual val bucket get() = android.bucket
    actual val name get() = android.name
    actual val path: String? get() = android.path
    actual val cacheControl get() = android.cacheControl
    actual val contentDisposition get() = android.contentDisposition
    actual val contentEncoding get() = android.contentEncoding
    actual val contentLanguage get() = android.contentLanguage
    actual val contentType get() = android.contentType
    actual val creationTimeMillis: Long? get() = android.creationTimeMillis
    actual val generation get() = android.generation
    actual val md5Hash get() = android.md5Hash
    actual val sizeBytes get() = android.sizeBytes
    actual val metadataGeneration get() = android.metadataGeneration
    actual val customMetadataKeys get() = android.customMetadataKeys.toList()
}