package com.adwi.cricket.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.adwi.cricket.R

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String,
) {
    AUTH(R.string.home, Icons.Outlined.Home, "home/auth"),
    HOME(R.string.home, Icons.Outlined.Home, "home/home"),
//    SEARCH(R.string.search, Icons.Outlined.Search, "home/search"),
//    FAVORITES(R.string.favorites, Icons.Outlined.Favorite, "home/favorites"),
//    SETTINGS(R.string.settings, Icons.Outlined.Settings, "home/settings")
}