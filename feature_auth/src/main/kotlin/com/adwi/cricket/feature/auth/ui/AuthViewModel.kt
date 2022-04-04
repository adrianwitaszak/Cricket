package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.core.State
import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.repository.UserRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel(
    private val userRepository: UserRepository,
    private val logger: Logger,

    ) : ViewModel() {

    private var _state: MutableStateFlow<AuthScreenState> = MutableStateFlow(AuthScreenState())
    val state: StateFlow<AuthScreenState> get() = _state

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            userRepository.getSignedInUser().collect { firebaseUser ->
                when (firebaseUser) {
                    is State.Success -> {
                        _state.update {
                            it.copy(
                                user = it.user,
                                loadingState = LoadingState.SUCCESS
                            )
                        }
                        logger.setUserId(firebaseUser.data?.id ?: "UnknownId")
                    }
                    is State.Loading -> {
                        _state.update {
                            it.copy(
                                loadingState = LoadingState.LOADING
                            )
                        }
                    }
                    is State.Failed -> {
                        _state.update {
                            it.copy(
                                user = null,
                                loadingState = LoadingState.FAILED(firebaseUser.message)
                            )
                        }
                    }
                    else -> {
                        _state.update {
                            it.copy(loadingState = LoadingState.IDLE)
                        }
                    }
                }
            }
        }
    }

    fun signInWithGoogle(credential: AuthCredential) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(loadingState = LoadingState.LOADING) }
                val result = userRepository.signInWithCredential(credential)
                _state.update {
                    it.copy(
                        user = result,
                        loadingState = if (result == null)
                            LoadingState.FAILED(msg = "Login Failed") else LoadingState.SUCCESS
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(loadingState = LoadingState.FAILED(e.localizedMessage ?: "Login Failed"))
                }
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
        Timber.d("signOut")
    }
}