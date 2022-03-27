package com.adwi.cricket.datasource.repository

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    ) : UserRepository {

    override suspend fun signInWithCredential(credential: AuthCredential): AuthResult? =
        firebaseAuth.signInWithCredential(credential).await()

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getCurrentUser(): Flow<FirebaseUser?> = flowOf(firebaseAuth.currentUser)
}