package com.adwi.cricket.feature.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adwi.cricket.core.LoadingState
import com.adwi.cricket.data.repository.repository.UserRepository
import com.adwi.cricket.data.repository.repository.UserRepositoryImpl
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
//    private val userRepository: UserRepositoryImpl
) : ViewModel() {

    private val _state = MutableStateFlow(AuthScreenState())
    val state: StateFlow<AuthScreenState> get() = _state

    fun intent(intent: AuthScreenEvent) {
        when (intent) {
            AuthScreenEvent.SignOut -> signOut()
            is AuthScreenEvent.SignWithCredential -> {
                signWithCredential(intent.credential)
            }
            AuthScreenEvent.StartWithoutSignIn -> startWithoutSignIn()
            AuthScreenEvent.None -> {}
        }
    }

    private fun startWithoutSignIn() = loadingStateFlow {
        //            TODO("get device IMEI as an id")
        val result = firebaseAuth.signInAnonymously()
        val l = result.result
        val name = l.user?.displayName
        val email = l.user?.email
        Timber.d("name = $name \nemail = $email")
    }

    private fun signWithCredential(credential: AuthCredential) = loadingStateFlow {
        val authResult = firebaseAuth.signInWithCredential(credential).await()
        Timber.d("result = ${authResult.user?.displayName ?: "no name"}")
        val uid = authResult?.user?.uid
        uid?.let {

        }
    }

    private fun signOut() {
        firebaseAuth.signOut()
    }

    private fun loadingStateFlow(content: suspend () -> Unit) =
        viewModelScope.launch {
            with(_state.value) {
                try {
                    loadingState = LoadingState.LOADING
                    content()
                    loadingState = LoadingState.SUCCESS
                } catch (e: Exception) {
                    loadingState = LoadingState.FAILED(e.localizedMessage)
                }
            }
        }
}