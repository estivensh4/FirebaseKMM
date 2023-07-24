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