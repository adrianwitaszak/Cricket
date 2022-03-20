package com.adwi.cricket.data.repository.repository

import com.adwi.cricket.data.local.CricketDatabase
import com.adwi.cricket.data.repository.mapper.toDomain
import com.adwi.cricket.data.repository.mapper.toEntity
import com.adwi.cricket.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    database: CricketDatabase
) : UserRepository {

    private val userDao = database.userDao()

    override suspend fun addUser(user: User) {
        userDao.insertUser(user.toEntity())
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }

    override fun getUser(): Flow<User?> = userDao.getUser().map { it.toDomain() }
}