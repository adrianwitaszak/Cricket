package com.adwi.cricket.app.ui.navigation

import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import com.adwi.cricket.R
import com.adwi.cricket.feature.auth.ui.AuthScreen
import com.adwi.cricket.feature.auth.ui.AuthViewModel
import com.adwi.cricket.feature.home.HomeScreen
import com.adwi.cricket.feature.home.HomeViewModel
import com.adwi.cricket.feature.onboarding.screens.OnBoardingScreen
import com.adwi.cricket.feature.onboarding.screens.OnBoardingViewModel
import com.google.accompanist.navigation.animation.composable

fun NavGraphBuilder.cricketNavGraph(
    onOnBoardingFinishedClick: () -> Unit,
    onSignOutClick: () -> Unit,
    goHome: () -> Unit,
) {
    composable(MainDestinations.HOME) { backStackEntry ->
        val viewModel = hiltViewModel<HomeViewModel>(backStackEntry)
        HomeScreen(
            viewModel = viewModel,
            onSignOutClick = onSignOutClick
        )
    }
    composable(
        route = MainDestinations.AUTH,
    ) { backStackEntry ->
        val viewModel = hiltViewModel<AuthViewModel>(backStackEntry)
        AuthScreen(
            viewModel = viewModel,
            appName = stringResource(id = R.string.app_name),
            goHome = goHome
        )
    }
    composable(
        route = MainDestinations.ONBOARDING,
    ) { backStackEntry ->
        val viewModel = hiltViewModel<OnBoardingViewModel>(backStackEntry)
        OnBoardingScreen(
            viewModel = viewModel,
            onOnBoardingFinishedClick = onOnBoardingFinishedClick
        )
    }
}

fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
