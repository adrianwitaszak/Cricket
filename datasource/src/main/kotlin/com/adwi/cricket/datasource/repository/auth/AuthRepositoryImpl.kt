package com.adwi.cricket.datasource.repository.auth

import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.mapper.UserMapper
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val userMapper: UserMapper,
    private val logger: Logger,
) : AuthRepository {

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun signInWithCredential(credential: AuthCredential): User? {
        val result = firebaseAuth.signInWithCredential(credential).await()
        val user = result.user?.let {
            userMapper.toUser(it)
        }
        return user
    }

    override fun signOut(): User? {
        firebaseAuth.signOut()
        return null
    }

}