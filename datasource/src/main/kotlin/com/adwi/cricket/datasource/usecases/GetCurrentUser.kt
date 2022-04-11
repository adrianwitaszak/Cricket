package com.adwi.cricket.datasource.usecases

import com.adwi.cricket.core.State
import com.adwi.cricket.datasource.repository.auth.AuthRepository
import com.adwi.cricket.datasource.repository.user.UserRepository
import com.adwi.cricket.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class GetCurrentUser(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {
    fun execute(): Flow<State<User>> = flow {
        emit(State.loading())
        val currentUser = authRepository.getCurrentUser()
        currentUser?.let { firebaseUser ->
            val remoteUser = userRepository.getSignedInUser(firebaseUser.uid)
            remoteUser?.let { user ->
                emit(State.Success(user))
                Timber.e("GetCurrentUser - success - $user")
            }
            Timber.e("GetCurrentUser - currentUser - $currentUser")
        } ?: emit(State.failed("Can't get user"))
        Timber.e("GetCurrentUser - failed - $currentUser")
    }.catch { e ->
        emit(State.failed(e.localizedMessage ?: "Can't get user"))
    }
}