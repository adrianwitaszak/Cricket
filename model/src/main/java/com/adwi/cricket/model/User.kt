package com.adwi.cricket.model

data class User(
    val id: String,
    val name: String = "",
    val email: String = "",
    val hasCompletedOnBoarding: Boolean = false,
)
