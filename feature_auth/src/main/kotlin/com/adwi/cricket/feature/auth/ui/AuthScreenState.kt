package com.adwi.cricket.feature.auth.ui

import com.adwi.cricket.core.LoadingState

data class AuthScreenState(
    val loadingState: LoadingState = LoadingState.IDLE,
)
