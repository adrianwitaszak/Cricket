package com.adwi.cricket.data.repository.user

import com.adwi.cricket.core.logger.Logger
import com.adwi.cricket.data.remote.RemoteDatasource
import com.adwi.cricket.data.remote.mapper.UserMapper
import com.adwi.cricket.model.User
import com.google.firebase.auth.FirebaseUser

class UserRepositoryImpl(
    private val remoteDatasource: RemoteDatasource,
    private val userMapper: UserMapper,
    private val logger: Logger,
) : UserRepository {

    override suspend fun getSignedInUser(uid: String): User? {
        return try {
            remoteDatasource.getUser(uid)
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
        } ?: logger.log("Cannot insert user")
    }
}