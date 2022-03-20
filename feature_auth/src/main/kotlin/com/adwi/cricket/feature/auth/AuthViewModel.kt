package com.adwi.cricket.feature.auth

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

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.IDLE)
    val loadingState: StateFlow<LoadingState> get() = _loadingState

    fun startWithoutSignIn() = viewModelScope.launch {
        try {
            _loadingState.emit(LoadingState.LOADING)
//            TODO("get device IMEI as an id")
            _loadingState.emit(LoadingState.SUCCESS)
        } catch (e: Exception) {
            _loadingState.emit(LoadingState.FAILED(e.localizedMessage))
        }
    }

    fun signWithCredential(credential: AuthCredential) = viewModelScope.launch {
        try {
            _loadingState.emit(LoadingState.LOADING)
            firebaseAuth.signInWithCredential(credential).await()
            _loadingState.emit(LoadingState.SUCCESS)
        } catch (e: Exception) {
            _loadingState.emit(LoadingState.FAILED(e.localizedMessage))
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}