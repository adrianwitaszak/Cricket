package com.adwi.cricket.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.adwi.cricket.R

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    AUTH(R.string.auth, Icons.Outlined.Person, "home/auth"),
    HOME(R.string.home, Icons.Outlined.Home, "home/home"),
}