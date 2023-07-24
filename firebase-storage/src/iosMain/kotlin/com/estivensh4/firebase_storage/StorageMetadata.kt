/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_storage

import cocoapods.FirebaseStorage.FIRStorageMetadata

actual class StorageMetadata(private val iOS: FIRStorageMetadata) {
    actual val reference get() = iOS.storageReference()?.let { StorageReference(it) }
    actual val bucket: String? get() = iOS.bucket()
    actual val name get() = iOS.name()
    actual val path get() = iOS.path()
    actual val cacheControl get() = iOS.cacheControl()
    actual val contentDisposition get() = iOS.contentDisposition()
    actual val contentEncoding get() = iOS.contentEncoding()
    actual val contentLanguage get() = iOS.contentLanguage()
    actual val contentType get() = iOS.contentType()
    actual val creationTimeMillis get() = iOS.timeCreated()?.timeIntervalSinceReferenceDate?.toLong()
    actual val generation: String? get() = iOS.generation().toString()
    actual val md5Hash get() = iOS.md5Hash()
    actual val sizeBytes get() = iOS.size()
    actual val metadataGeneration: String? get() = iOS.metageneration().toString()
    @Suppress("UNCHECKED_CAST")
    actual val customMetadataKeys get() = iOS.customMetadata() as List<String>
}