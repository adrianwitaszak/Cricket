package com.adwi.cricket.datasource.usecases

import com.adwi.cricket.datasource.repository.auth.AuthRepository

class SignOut(
    private val authRepository: AuthRepository,
) {
    fun execute() {
        authRepository.signOut()
    }
}