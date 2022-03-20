package com.adwi.cricket.data.local.entity

import androidx.room.Entity

@Entity(tableName = "user_table")
data class UserEntity(
    val id: String,
    val name: String = "",
    val email: String = "",
    val hasCompletedOnBoarding: Boolean = false,
)
