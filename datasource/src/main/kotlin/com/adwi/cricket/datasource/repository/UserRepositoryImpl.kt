package com.adwi.cricket.datasource.repository

import com.adwi.cricket.datasource.local.CricketDatabase
import com.adwi.cricket.datasource.mapper.toDomain
import com.adwi.cricket.datasource.mapper.toEntity
import com.adwi.cricket.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    database: CricketDatabase,
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