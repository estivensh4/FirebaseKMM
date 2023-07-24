/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

package com.estivensh4.firebase_storage

sealed class UploadResult {
    data class Success(
        val storage: StorageReference,
        val error: Throwable,
        val progress: Progress,
        val metadata: StorageMetadata?
    ) : UploadResult()

    data class Progress(
        val bytesTransferred: Number,
        val totalByteCount: Number
    ) : UploadResult()

    object Paused : UploadResult()
}