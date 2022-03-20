package com.adwi.cricket.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import com.adwi.cricket.R
import com.adwi.cricket.feature.auth.ui.AuthScreen
import com.adwi.cricket.feature.auth.ui.AuthViewModel
import com.adwi.cricket.feature.home.HomeScreen
import com.adwi.cricket.feature.home.HomeViewModel
import com.adwi.cricket.feature_onboarding.screens.OnBoardingScreen
import com.adwi.cricket.feature_onboarding.screens.OnBoardingViewModel
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.cricketNavGraph(
    onStartWithoutSignInClick: () -> Unit,
    onOnBoardingFinishedClick: () -> Unit,
    onSignOut: () -> Unit,
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.HOME.route
    ) {
        addHomeGraph()

        composable(
            route = MainDestinations.AUTH_ROUTE,
        ) { backStackEntry ->
            val viewModel = hiltViewModel<AuthViewModel>(backStackEntry)
            val state by viewModel.state.collectAsState()
            AuthScreen(
                viewModel = viewModel,
                appName = stringResource(id = R.string.app_name),
                goHome = onStartWithoutSignInClick
            )
        }
        composable(
            route = MainDestinations.ONBOARDING_ROUTE,
        ) { backStackEntry ->
            val viewModel = hiltViewModel<OnBoardingViewModel>(backStackEntry)
            OnBoardingScreen(
                viewModel = viewModel,
                onOnBoardingFinishedClick = onOnBoardingFinishedClick
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHomeGraph(

) {
    composable(HomeSections.HOME.route) { backStackEntry ->
        val viewModel = hiltViewModel<HomeViewModel>(backStackEntry)
        HomeScreen(
            viewModel = viewModel,
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
