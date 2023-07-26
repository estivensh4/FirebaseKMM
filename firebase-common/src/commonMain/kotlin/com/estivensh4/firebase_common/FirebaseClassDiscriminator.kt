/*
 * *
 *  * Created by estiven on 26/7/23 15:02
 *  * Copyright (c) 2023 . All rights reserved.
 *  * Last modified 15/7/23 00:37
 *
 */

package com.estivensh4.firebase_common

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InheritableSerialInfo

@OptIn(ExperimentalSerializationApi::class)
@InheritableSerialInfo
@Target(AnnotationTarget.CLASS)
annotation class FirebaseClassDiscriminator(val discriminator: String)