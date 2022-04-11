package com.adwi.cricket.data.usecases

import com.adwi.cricket.data.repository.auth.AuthRepository
import com.adwi.cricket.data.repository.user.UserRepository
import com.adwi.cricket.model.User

class GetCurrentUser(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) {
    suspend fun execute(): User? {
        val firebaseUser = authRepository.getCurrentUser()
        val user = firebaseUser?.let {
            userRepository.getSignedInUser(it.uid)
        }
        return user
    }
}