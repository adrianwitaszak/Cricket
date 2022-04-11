package com.adwi.cricket.datasource.repository.auth

import com.adwi.cricket.datasource.logger.Logger
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val logger: Logger,
) : AuthRepository {

    override fun getCurrentUser(): FirebaseUser? {
        val firebaseUser = firebaseAuth.currentUser
        firebaseUser?.let {
            logger.setUserId(it.uid)
        }
        return firebaseUser
    }

    override suspend fun signInWithCredential(credential: AuthCredential): FirebaseUser? {
        val result = firebaseAuth.signInWithCredential(credential).await()
        return result.user
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}