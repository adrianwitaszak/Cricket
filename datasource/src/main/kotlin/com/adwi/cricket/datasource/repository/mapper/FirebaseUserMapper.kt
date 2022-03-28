package com.adwi.cricket.datasource.repository.mapper

import com.adwi.cricket.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser() = User(
    id = uid,
    name = displayName.orEmpty(),
    email = email.orEmpty(),
    hasCompletedOnBoarding = false,
)