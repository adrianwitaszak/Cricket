package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.core.State
import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.repository.UserRepository
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel(
    private val userRepository: UserRepository,
    private val logger: Logger
) : ViewModel() {

    private var _loadingState: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.IDLE)
    val loadingState: StateFlow<LoadingState> get() = _loadingState

    private var _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> get() = _user

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            userRepository.getSignedInUser().collectLatest { firebaseUser ->
                when (firebaseUser) {
                    is State.Success -> {
                        _user.value = firebaseUser.data
                        _loadingState.value = LoadingState.SUCCESS
                        logger.setUserId(firebaseUser.data?.id ?: "UnknownId")
                    }
                    is State.Loading -> {
                        _loadingState.value = LoadingState.LOADING
                    }
                    is State.Failed -> {
                        _user.value = null
                        _loadingState.value = LoadingState.FAILED(firebaseUser.message)
                    }
                    else -> {
                        _user.value = null
                        _loadingState.value = LoadingState.IDLE
                    }
                }
                val message = """
                    getCurrentUser -
                    User = ${user.value}
                    Name = ${user.value?.name}
                    loading = ${loadingState.value}
                """.trimIndent()
                Timber.d(message)
            }
        }
    }

    fun signInWithGoogle(credential: AuthCredential) {
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                val result = userRepository.signInWithCredential(credential)
                _user.value = result
                _loadingState.value = if (result == null)
                    LoadingState.FAILED(msg = "Login Failed") else LoadingState.SUCCESS
            } catch (e: Exception) {
                _loadingState.value = LoadingState.FAILED(e.localizedMessage ?: "Login Failed")
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
        Timber.d("signOut")
    }
}