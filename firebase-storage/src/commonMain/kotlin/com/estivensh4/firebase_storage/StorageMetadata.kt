package com.estivensh4.firebase_storage

expect class StorageMetadata {
    val reference: StorageReference?
    val bucket: String?
    val name: String?
    val path: String?
    val cacheControl: String?
    val contentDisposition: String?
    val contentEncoding: String?
    val contentLanguage: String?
    val contentType: String?
    val creationTimeMillis: Long?
    val generation: String?
    val md5Hash: String?
    val sizeBytes: Long
    val metadataGeneration: String?
    val customMetadataKeys: List<String>
}