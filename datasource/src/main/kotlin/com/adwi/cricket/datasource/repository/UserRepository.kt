package com.adwi.cricket.datasource.repository

import com.adwi.cricket.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun signInWithCredential(credential: AuthCredential): User?

    fun signOut(): User?

    fun getCurrentUser(): Flow<User?>
}