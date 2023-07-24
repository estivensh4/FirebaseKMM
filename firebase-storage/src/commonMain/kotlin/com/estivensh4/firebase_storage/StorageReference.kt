package com.estivensh4.firebase_storage

expect class StorageReference {
    val storage: FirebaseStorage
    val name: String
    val path: String
    val parent: StorageReference?
    val bucket: String
    val root: StorageReference
    suspend fun downloadUrl(): String?
    fun child(pathString: String): StorageReference
    suspend fun metadata(): StorageMetadata
    suspend fun delete()
    fun putFile(file: File): Progress
}