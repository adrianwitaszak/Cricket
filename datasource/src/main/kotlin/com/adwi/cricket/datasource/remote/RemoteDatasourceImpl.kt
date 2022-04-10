package com.adwi.cricket.datasource.remote

import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.repository.user.COLLECTION_USERS
import com.adwi.cricket.model.User
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RemoteDatasourceImpl(
    dbFirestore: FirebaseFirestore,
    private val logger: Logger,
) : RemoteDatasource {

    private val usersCollection = dbFirestore.collection(COLLECTION_USERS)

    override suspend fun getUser(userId: String): DocumentSnapshot? {
        return usersCollection.document(userId).get().await()
    }

    override suspend fun insertUser(user: User) {
        usersCollection.document().set(user).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> logger.log("insertUser - success")
                else -> logger.log(
                    message = "Cannot insert user",
                    keyValue = user.id,
                )
            }
        }
    }

}