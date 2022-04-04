package com.adwi.cricket.feature.auth.ui

import com.google.firebase.auth.AuthCredential

sealed class AuthScreenIntent {
    data class SignIntWithCredentials(val credential: AuthCredential) : AuthScreenIntent()
}