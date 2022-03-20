package com.adwi.cricket.datasource.repository

import com.adwi.cricket.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    fun getUser() : Flow<User?>
}