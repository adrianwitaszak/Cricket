package com.adwi.cricket.datasource.repository

import com.adwi.cricket.datasource.mapper.toUser
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    ) : UserRepository {

    override suspend fun signInWithCredential(credential: AuthCredential): User? {
        val result = firebaseAuth.signInWithCredential(credential).await()
        return result.user?.toUser()
    }

    override fun signOut(): User? {
        firebaseAuth.signOut()
        return null
    }

    override fun getCurrentUser(): Flow<User?> = flowOf(firebaseAuth.currentUser?.toUser())
}