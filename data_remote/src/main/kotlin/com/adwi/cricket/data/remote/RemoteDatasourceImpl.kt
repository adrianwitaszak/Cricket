package com.adwi.cricket.data.remote

import com.adwi.cricket.core.logger.LogType
import com.adwi.cricket.core.logger.Logger
import com.adwi.cricket.data.remote.mapper.UserMapper
import com.adwi.cricket.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

private const val COLLECTION_USERS = "users"

class RemoteDatasourceImpl(
    dbFirestore: FirebaseFirestore,
    private val mapper: UserMapper,
    private val logger: Logger,
) : RemoteDatasource {

    private val usersCollection = dbFirestore.collection(COLLECTION_USERS)

    override suspend fun getUser(userId: String): User? {
        val userSnapshot = usersCollection.document(userId).get().await()
        logger.log(
            message = "getUser = ${userSnapshot.id}",
            logType = LogType.Info
        )
        return mapper.toUser(userSnapshot)
    }

    override suspend fun insertUser(user: User) {
        usersCollection.document(user.id).set(user).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> logger.log("insertUser - success", logType = LogType.Info)
                else -> logger.log(
                    message = "Cannot insert user",
                    keyValue = user.id,
                )
            }
        }
    }
}