package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.core.State
import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.repository.auth.AuthRepository
import com.adwi.cricket.datasource.repository.user.UserRepository
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val logger: Logger,

    ) : ViewModel() {

    private var _state: MutableStateFlow<AuthScreenState> = MutableStateFlow(AuthScreenState())
    val state: StateFlow<AuthScreenState> get() = _state

    init {
        getCurrentUser()
    }

    fun triggerIntent(intent: AuthScreenIntent) {
        when (intent) {
            is AuthScreenIntent.SignIntWithCredentials -> signInWithGoogle(intent.credential)
            AuthScreenIntent.SignIntWithOutGoogle -> {}
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            val uid = authRepository.getCurrentUser()
            uid?.let { firebaseUser ->
                userRepository.getSignedInUser(firebaseUser.uid).collect { userState ->
                    when (userState) {
                        is State.Success -> {
                            _state.update {
                                it.copy(
                                    user = it.user,
                                    loadingState = LoadingState.SUCCESS
                                )
                            }
                            logger.setUserId(userState.data?.id ?: "UnknownId")
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
                                    loadingState = LoadingState.FAILED(userState.message)
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
    }

    private fun signInWithGoogle(credential: AuthCredential) {
        viewModelScope.launch {
            try {
                _state.update { it.copy(loadingState = LoadingState.LOADING) }
                val firebaseUser = authRepository.signInWithCredential(credential)
                firebaseUser?.let {
                    val user = userRepository.insertUser(it)
                    _state.update { screenState ->
                        screenState.copy(
                            user = user,
                            loadingState = if (user == null)
                                LoadingState.FAILED(msg = "Login Failed") else LoadingState.SUCCESS
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(loadingState = LoadingState.FAILED(e.localizedMessage ?: "Login Failed"))
                }
            }
        }
    }

    fun signOut() {
        authRepository.signOut()
        Timber.d("signOut")
    }
}