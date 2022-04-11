package com.adwi.cricket.data.usecases

import com.adwi.cricket.data.repository.auth.AuthRepository

class SignOut(
    private val authRepository: AuthRepository,
) {
    fun execute() {
        authRepository.signOut()
    }
}