package com.adwi.cricket.datasource.mapper

import com.adwi.cricket.datasource.local.entity.UserEntity
import com.adwi.cricket.model.User

fun User.toEntity() = UserEntity(
    id = this.id,
    name = this.name,
    email = this.email,
    hasCompletedOnBoarding = this.hasCompletedOnBoarding
)

fun UserEntity.toDomain() = User(
    id = this.id,
    name = this.name,
    email = this.email,
    hasCompletedOnBoarding = this.hasCompletedOnBoarding
)