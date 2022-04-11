package com.adwi.cricket.data.remote

import com.adwi.cricket.model.User

interface RemoteDatasource {
    suspend fun getUser(userId: String): User?
    suspend fun insertUser(user: User)
}