package com.adwi.cricket.data.repository

import com.adwi.cricket.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    fun getUser(id: String) : Flow<User>
}