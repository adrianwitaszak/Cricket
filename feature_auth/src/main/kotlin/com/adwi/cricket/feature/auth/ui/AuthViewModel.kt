package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.datasource.repository.UserRepositoryImpl
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
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

    private var _user: MutableStateFlow<FirebaseUser?> = MutableStateFlow(null)
    val user: StateFlow<FirebaseUser?> get() = _user

    init {
        getCurrentUser()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            userRepository.getCurrentUser().collect { firebaseUser ->
                _loadingState.value =
                    if (firebaseUser == null) LoadingState.IDLE else LoadingState.SUCCESS

                Timber.d("\ngetCurrentUser - \nUser = ${firebaseUser?.displayName ?: "No user"} \nloading = ${loadingState.value}")
            }
        }
    }

    fun signInWithGoogle(credential: AuthCredential) {
        viewModelScope.launch {
            try {
                Timber.d("loadingStateFlow - Idle")
                _loadingState.value = LoadingState.LOADING
                Timber.d("loadingStateFlow - Loading")
                val authResult = userRepository.signInWithCredential(credential)

                if (authResult?.user == null) {
                    _loadingState.value = LoadingState.FAILED(msg = "Login Failed")
                    Timber.d("loadingStateFlow - Failed")
                } else {
                    _loadingState.value = LoadingState.SUCCESS
                    _user.value = authResult.user
                    Timber.d("signInWithGoogle - result = ${authResult.user?.displayName}")
                }

                Timber.d("loadingStateFlow - Success")
            } catch (e: Exception) {
                _loadingState.value = LoadingState.FAILED(e.localizedMessage ?: "Login Failed")
                Timber.d("loadingStateFlow - Failed")
            }
        }
    }

    fun signOut() {
        userRepository.signOut()
        Timber.d("signOut")
    }
}