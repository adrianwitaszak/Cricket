package com.adwi.cricket.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adwi.cricket.data.local.dao.UserDao
import com.adwi.cricket.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class CricketDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}