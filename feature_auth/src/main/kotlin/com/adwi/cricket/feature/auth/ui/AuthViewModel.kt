package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.datasource.mapper.toUser
import com.adwi.cricket.datasource.repository.UserRepositoryImpl
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepositoryImpl,
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
                val authResult = userRepository.signInWithCredential(credential)
                if (authResult?.user == null) {
                    _loadingState.value = LoadingState.FAILED(msg = "Login Failed")
                    return@launch
                } else {
                    _loadingState.value = LoadingState.SUCCESS
                    _user.value = authResult.user?.toUser()
                }
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