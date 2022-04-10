package com.adwi.cricket.datasource.repository.auth

import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithCredential(credential: AuthCredential): User?
    fun signOut(): User?
}