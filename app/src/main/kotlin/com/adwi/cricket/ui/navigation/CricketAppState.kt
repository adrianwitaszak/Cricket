package com.adwi.cricket.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

object MainDestinations {
    const val AUTH_ROUTE = "auth"
    const val ONBOARDING_ROUTE = "onboarding"
    const val HOME_ROUTE = "home"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun rememberMyAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberAnimatedNavController(),
) =
    remember(scaffoldState, navController) {
        PexAppState(scaffoldState, navController)
    }

@Stable
class PexAppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
) {
    val bottomBarTabs = HomeSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }
}