package com.estivensh4.firebase_storage

import kotlinx.coroutines.flow.Flow

interface Progress : Flow<UploadResult> {
    fun pause()
    fun resume()
    fun cancel()
}