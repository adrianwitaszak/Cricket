package com.adwi.cricket.core

sealed class LoadingState {
    object Loading : LoadingState()
    object SUCCESS : LoadingState()
    data class FAILED(val msg: String?) : LoadingState()
    object IDLE : LoadingState()
}
