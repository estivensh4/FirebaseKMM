/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

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