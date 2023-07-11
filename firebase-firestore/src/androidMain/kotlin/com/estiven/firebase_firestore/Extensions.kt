package com.estiven.firebase_firestore

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

fun com.google.firebase.firestore.DocumentChange.Type.toAndroid(): DocumentType {
    return when (this) {
        com.google.firebase.firestore.DocumentChange.Type.ADDED -> DocumentType.ADDED
        com.google.firebase.firestore.DocumentChange.Type.MODIFIED -> DocumentType.MODIFIED
        com.google.firebase.firestore.DocumentChange.Type.REMOVED -> DocumentType.REMOVED
    }
}

fun <T : Any> mapToObject(map: Map<String, Any>, clazz: KClass<T>): T {
    val constructor = clazz.constructors.first()
    clazz.memberProperties.associateWith { map[it.name] }
    val args = constructor.parameters.associateWith { map[it.name] }
    return constructor.callBy(args)
}