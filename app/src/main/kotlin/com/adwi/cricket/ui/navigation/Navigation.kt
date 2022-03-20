package com.adwi.cricket.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import com.adwi.cricket.feature.auth.AuthScreen
import com.adwi.cricket.feature.auth.AuthViewModel
import com.google.accompanist.navigation.animation.composable
import com.adwi.cricket.R
import com.adwi.cricket.feature_onboarding.screens.OnBoardingScreen
import com.adwi.cricket.feature_onboarding.screens.OnBoardingViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.myNavGraph(

) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.HOME.route
    ) {
        addHomeGraph(
        )
    }
    composable(
        route = MainDestinations.AUTH_ROUTE,
    ) { backStackEntry ->
        val viewModel = hiltViewModel<AuthViewModel>(backStackEntry)
        AuthScreen(
            viewModel = viewModel,
            appName = stringResource(id = R.string.app_name)
        )
    }
    composable(
        route = MainDestinations.ONBOARDING_ROUTE,
    ) { backStackEntry ->
        val viewModel = hiltViewModel<OnBoardingViewModel>(backStackEntry)
        OnBoardingScreen(
            viewModel = viewModel,
            questions = emptyList() // TODO(create questions)
        )
    }

}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHomeGraph(

) {
//    composable(HomeSections.HOME.route) { backStackEntry ->
//        val viewModel = hiltViewModel<HomeViewModel>(backStackEntry)
//
//        HomeScreen(
//            viewModel = viewModel,
//        )
//    }
}

fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}
