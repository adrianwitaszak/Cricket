package com.adwi.cricket.datasource.repository.user

import com.adwi.cricket.core.State
import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.mapper.UserMapper
import com.adwi.cricket.datasource.remote.RemoteDatasource
import com.adwi.cricket.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(
    private val remoteDatasource: RemoteDatasource,
    private val userMapper: UserMapper,
    private val logger: Logger,
) : UserRepository {

    override fun getSignedInUser(uid: String): Flow<State<User?>> = flow {
        emit(State.loading())
        val user = getUser(uid)
        emit(State.success(user))
    }.catch {
        emit(State.failed(it.message ?: "Cannot get user"))
    }

    override suspend fun insertUser(user: User) {
        remoteDatasource.insertUser(user)
    }

    private suspend fun getUser(userId: String): User? {
        return try {
            val userSnapshot = remoteDatasource.getUser(userId)
            userSnapshot?.let {
                return userMapper.toUser(userSnapshot)
            }
            null
        } catch (e: Exception) {
            logger.log(
                message = "Cannot get user",
                keyValue = userId,
                exception = e
            )
            null
        }
    }
}

internal const val COLLECTION_USERS = "users"