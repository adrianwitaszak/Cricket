package com.adwi.cricket.datasource.repository.user

import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.mapper.UserMapper
import com.adwi.cricket.datasource.remote.RemoteDatasource
import com.adwi.cricket.model.User
import com.google.firebase.auth.FirebaseUser

class UserRepositoryImpl(
    private val remoteDatasource: RemoteDatasource,
    private val userMapper: UserMapper,
    private val logger: Logger,
) : UserRepository {

    override suspend fun getSignedInUser(uid: String): User? {
        return try {
            val userSnapshot = remoteDatasource.getUser(uid)
            userSnapshot?.let {
                return userMapper.toUser(userSnapshot)
            }
            null
        } catch (e: Exception) {
            logger.log(
                message = "Cannot get user",
                keyValue = uid,
                exception = e
            )
            null
        }
    }

    override suspend fun insertUser(firebaseUser: FirebaseUser) {
        val user = userMapper.toUser(firebaseUser)
        user?.let {
            remoteDatasource.insertUser(user)
        } ?: logger.log("Can't insert user")
    }
}

internal const val COLLECTION_USERS = "users"