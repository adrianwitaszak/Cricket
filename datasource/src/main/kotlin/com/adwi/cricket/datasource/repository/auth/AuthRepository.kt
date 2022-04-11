package com.adwi.cricket.datasource.repository.auth

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun getCurrentUser(): FirebaseUser?
    suspend fun signInWithCredential(credential: AuthCredential): FirebaseUser?
    fun signOut()
}