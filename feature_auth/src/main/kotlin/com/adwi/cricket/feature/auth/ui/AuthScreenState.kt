package com.adwi.cricket.feature.auth.ui

import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.model.User

data class AuthScreenState(
    val loadingState: LoadingState = LoadingState.IDLE,
    val user: User? = null,
)
