package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : ViewModel() {

    private val _state = MutableStateFlow<AuthScreenState>(AuthScreenState())
    val state: StateFlow<AuthScreenState> get() = _state

    fun intent(intent: AuthScreenEvent) {
        when (intent) {
            AuthScreenEvent.SignOut -> signOut()
            is AuthScreenEvent.SignWithCredential -> {
                signWithCredential(intent.credential)
            }
            AuthScreenEvent.StartWithoutSignIn -> startWithoutSignIn()
        }
    }

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.IDLE)
    val loadingState: StateFlow<LoadingState> get() = _loadingState

    private fun startWithoutSignIn() = loadingStateFlow {
        //            TODO("get device IMEI as an id")
    }

    private fun signWithCredential(credential: AuthCredential) = loadingStateFlow {
        firebaseAuth.signInWithCredential(credential).await()
    }

    private fun signOut() {
        firebaseAuth.signOut()
    }

    private fun loadingStateFlow(content: suspend () -> Unit) =
        viewModelScope.launch {
            try {
                _loadingState.emit(LoadingState.LOADING)
                content()
                _loadingState.emit(LoadingState.SUCCESS)
            } catch (e: Exception) {
                _loadingState.emit(LoadingState.FAILED(e.localizedMessage))
            }
    }
}