package com.adwi.cricket.datasource.repository

import com.adwi.cricket.core.State
import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.repository.mapper.UserMapper
import com.adwi.cricket.model.User
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class UserRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val dbFirestore: FirebaseFirestore,
    private val userMapper: UserMapper,
    private val logger: Logger,
) : UserRepository {

    private val usersCollection = dbFirestore.collection(COLLECTION_USERS)

    override suspend fun signInWithCredential(credential: AuthCredential): User? {
        val result = firebaseAuth.signInWithCredential(credential).await()
        val user = result.user?.let {
            userMapper.toUser(it)
        }
        return user
    }

    override fun signOut(): User? {
        firebaseAuth.signOut()
        return null
    }

    override fun getSignedInUser(): Flow<State<User?>> = flow {
        emit(State.loading())
        val signedUser = firebaseAuth.currentUser
        signedUser?.let {
            val userId = it.uid
            val user = getUser(userId)
            emit(State.success(user))
        }
    }.catch {
        emit(State.failed(it.message ?: "Cannot get user"))
    }

    override suspend fun insertUser(user: User) {
        usersCollection.document().set(user).addOnCompleteListener { task ->
            when {
                task.isSuccessful -> logger.log("insertUser - success")
                else -> logger.logErrorToCrashlytics(
                    message = "Cannot insert user",
                    keyValue = user.id,
                )
            }
        }
    }

    private suspend fun getUser(userId: String): User? {
        return try {
            val userSnapshot = usersCollection.document(userId).get().await()
            userMapper.toUser(userSnapshot)
        } catch (e: Exception) {
            logger.logErrorToCrashlytics(
                message = "Cannot get user",
                keyValue = userId,
                exception = e
            )
            null
        }
    }
}

internal const val COLLECTION_USERS = "users"