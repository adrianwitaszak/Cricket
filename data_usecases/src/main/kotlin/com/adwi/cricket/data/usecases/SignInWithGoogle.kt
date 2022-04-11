package com.adwi.cricket.data.usecases

import com.adwi.cricket.core.State
import com.adwi.cricket.data.repository.auth.AuthRepository
import com.adwi.cricket.data.repository.user.UserRepository
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SignInWithGoogle(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {
    fun execute(credential: AuthCredential): Flow<State<User>> = flow {
        emit(State.loading())
        val firebaseUser = authRepository.signInWithCredential(credential)
        firebaseUser?.let {
            userRepository.insertUser(it)
            val user = userRepository.getSignedInUser(it.uid)
            user?.let {
                emit(State.Success(user))
            }
        }
    }.catch { e ->
        emit(State.failed(e.localizedMessage ?: "Can't sign in user"))
    }
}