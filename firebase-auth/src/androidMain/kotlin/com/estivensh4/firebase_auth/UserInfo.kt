package com.estivensh4.firebase_auth

internal fun com.google.firebase.auth.UserInfo.toAndroid(): UserInfo {
    return UserInfo(
        photoUrl = photoUrl.toString(),
        displayName = displayName,
        email = email,
        phoneNumber = phoneNumber,
        providerId = providerId,
        uid = uid,
        isEmailVerified = isEmailVerified
    )
}