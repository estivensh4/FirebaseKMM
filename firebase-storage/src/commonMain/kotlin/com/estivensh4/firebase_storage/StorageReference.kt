/*
 * *
 *  * Created by estiven on 24/7/23 00:29
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 23/7/23 19:28
 *
 */

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