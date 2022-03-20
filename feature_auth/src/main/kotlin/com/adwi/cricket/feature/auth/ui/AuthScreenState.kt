package com.adwi.cricket.feature.auth.ui

import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.model.User

data class AuthScreenState(
    var loadingState: LoadingState = LoadingState.IDLE,
    var user: User? = null
)
