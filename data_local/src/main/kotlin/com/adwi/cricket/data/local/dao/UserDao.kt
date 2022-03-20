package com.adwi.cricket.data.local.dao

import androidx.room.*
import com.adwi.cricket.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user_table")
    fun getUser(): Flow<UserEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: UserEntity)
}