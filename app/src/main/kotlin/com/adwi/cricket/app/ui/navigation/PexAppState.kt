package com.adwi.cricket.app.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

object MainDestinations {
    const val AUTH = "auth"
    const val ONBOARDING = "onboarding"
    const val HOME = "home"
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
    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToOnBoarding() {
        navController.navigate(MainDestinations.ONBOARDING)
    }

    fun navigateToHome(fromAuth: Boolean = false) {
        navController.navigate(MainDestinations.HOME) {
            if (fromAuth) {
                popUpTo(MainDestinations.HOME) { inclusive = true }
            }
            launchSingleTop = true
        }
    }

    fun navigateToAuth() {
        navController.navigate(MainDestinations.AUTH) {
            popUpTo(MainDestinations.AUTH) { inclusive = true }

        }
    }
}