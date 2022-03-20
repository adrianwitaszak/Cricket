package com.adwi.cricket.feature.auth.ui

import com.google.firebase.auth.AuthCredential

sealed class AuthScreenEvent {
    object None : AuthScreenEvent()
    object SignOut : AuthScreenEvent()
    object StartWithoutSignIn : AuthScreenEvent()
    data class SignWithCredential(val credential: AuthCredential) : AuthScreenEvent()
}
