package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.datasource.repository.UserRepository
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel(
    private val userRepository: UserRepository,
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
            userRepository.getCurrentUser().collect { firebaseUser ->
                if (firebaseUser == null) {
                    _user.value = null
                    _loadingState.value = LoadingState.IDLE
                } else {
                    _user.value = firebaseUser
                    _loadingState.value = LoadingState.SUCCESS
                }
                val message = """
                    getCurrentUser -
                    User = $firebaseUser
                    Name = ${firebaseUser?.name}
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