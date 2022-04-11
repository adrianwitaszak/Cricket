package com.adwi.cricket.data.repository.user

import com.adwi.cricket.model.User
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

    suspend fun getSignedInUser(uid: String): User?
    suspend fun insertUser(firebaseUser: FirebaseUser)
}