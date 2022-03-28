package com.adwi.cricket.datasource.repository.mapper

import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.model.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot

class UserMapper(
    private val logger: Logger,
) {
    fun toUser(firebaseUser: FirebaseUser): User? {
        return try {
            val id = firebaseUser.uid
            val name = firebaseUser.displayName.orEmpty()
            val email = firebaseUser.email.orEmpty()
            User(id, name, email)
        } catch (exception: Exception) {
            logger.logErrorToCrashlytics(
                message = "Error converting user profile",
                key = "userId",
                keyValue = firebaseUser.uid,
                exception = exception
            )
            null
        }
    }

    fun toUser(snapshot: DocumentSnapshot): User? {
        return try {
            val id = snapshot.getString("id") ?: ""
            val name = snapshot.getString("name") ?: ""
            val email = snapshot.getString("email") ?: ""
            User(id, name, email)
        } catch (exception: Exception) {
            logger.logErrorToCrashlytics(
                message = "Error converting user profile",
                key = "userId",
                keyValue = snapshot.id,
                exception = exception
            )
            null
        }
    }
}