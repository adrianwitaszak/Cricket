package com.adwi.cricket.datasource.usecases

import com.adwi.cricket.datasource.repository.auth.AuthRepository
import com.adwi.cricket.datasource.repository.user.UserRepository
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