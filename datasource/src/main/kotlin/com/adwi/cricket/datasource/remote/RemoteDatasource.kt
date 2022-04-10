package com.adwi.cricket.datasource.remote

import com.adwi.cricket.model.User
import com.google.firebase.firestore.DocumentSnapshot

interface RemoteDatasource {

    suspend fun getUser(userId: String): DocumentSnapshot?
    suspend fun insertUser(user: User)
}