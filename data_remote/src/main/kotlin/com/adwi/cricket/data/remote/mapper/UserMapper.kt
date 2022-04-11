package com.adwi.cricket.data.remote.mapper

import com.adwi.cricket.core.logger.LogType
import com.adwi.cricket.core.logger.Logger
import com.adwi.cricket.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot

class UserMapper(
    private val logger: Logger,
) {
    fun toUser(firebaseUser: FirebaseUser?): User? {
        return try {
            val id = firebaseUser?.uid.orEmpty()
            val name = firebaseUser?.displayName.orEmpty()
            val email = firebaseUser?.email.orEmpty()
            logUser(id, name, email)
            User(id, name, email)
        } catch (exception: Exception) {
            logUserError(
                key = firebaseUser?.uid ?: "",
                exception
            )
            null
        }
    }

    fun toUser(snapshot: DocumentSnapshot): User? {
        return try {
            val id = snapshot.getString("id") ?: ""
            val name = snapshot.getString("name") ?: ""
            val email = snapshot.getString("email") ?: ""
            logUser(id, name, email)
            User(id, name, email)
        } catch (exception: Exception) {
            logUserError(
                key = snapshot.id,
                exception
            )
            null
        }
    }

    private fun logUser(id: String, name: String, email: String) {
        logger.log(
            message = "UserMapper - toUser\n$id $name $email",
            logType = LogType.Info
        )
    }

    private fun logUserError(key: String, exception: Exception) {
        logger.log(
            message = "Error converting user profile",
            key = "userId",
            keyValue = key,
            exception = exception,
            logType = LogType.Error
        )
    }
}