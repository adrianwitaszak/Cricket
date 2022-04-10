package com.adwi.cricket.datasource.repository.user

import com.adwi.cricket.core.State
import com.adwi.cricket.model.User
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getSignedInUser(uid: String): Flow<State<User?>>
    suspend fun insertUser(user: FirebaseUser): User?
}