package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.core.State
import com.adwi.cricket.datasource.usecases.GetCurrentUser
import com.adwi.cricket.datasource.usecases.SignInWithGoogle
import com.adwi.cricket.datasource.usecases.SignOut
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthViewModel(
    private val getCurrentUser: GetCurrentUser,
    private val signInWithGoogle: SignInWithGoogle,
    private val signOut: SignOut,
) : ViewModel() {

    private var _state: MutableStateFlow<AuthScreenState> = MutableStateFlow(AuthScreenState())
    val state: StateFlow<AuthScreenState> get() = _state

    init {
        Timber.e("AuthViewModel init")
        getCurrentUser()
    }

    fun triggerIntent(intent: AuthScreenIntent) {
        when (intent) {
            is AuthScreenIntent.SignIntWithCredentials -> signInWithGoogle(intent.credential)
            AuthScreenIntent.SignIntWithOutGoogle -> {}
            AuthScreenIntent.SignOut -> signOutUser()
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            getCurrentUser.execute().collect { userState ->
                Timber.e("getCurrentUser ")
                handleUserState(userState)
            }
        }
    }

    private fun signInWithGoogle(credential: AuthCredential) {
        viewModelScope.launch {
            signInWithGoogle.execute(credential).collect { userState ->
                Timber.e("SignInWithGoogle - viewmodel success - $userState")
                handleUserState(userState)
            }
        }
    }

    private fun signOutUser() {
        signOut.execute()
    }

    private fun handleUserState(userState: State<User>) {
        when (userState) {
            is State.Success -> {
                _state.update {
                    it.copy(
                        user = it.user,
                        loadingState = LoadingState.SUCCESS
                    )
                }
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