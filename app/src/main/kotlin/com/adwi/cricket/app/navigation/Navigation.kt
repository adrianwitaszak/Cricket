package com.adwi.cricket.app.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import com.adwi.cricket.R
import com.adwi.cricket.feature.auth.ui.AuthScreen
import com.adwi.cricket.feature.auth.ui.AuthViewModel
import com.adwi.cricket.feature.home.HomeScreen
import com.adwi.cricket.feature.home.HomeViewModel
import com.adwi.cricket.feature.onboarding.screens.OnBoardingScreen
import com.adwi.cricket.feature.onboarding.screens.OnBoardingViewModel
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.inject

fun NavGraphBuilder.cricketNavGraph(
    onOnBoardingFinishedClick: () -> Unit,
    onSignOutClick: () -> Unit,
    goHome: () -> Unit,
) {
    composable(MainDestinations.HOME) {
        val viewModel: HomeViewModel by inject()
        HomeScreen(
            viewModel = viewModel,
            onSignOutClick = onSignOutClick
        )
    }
    composable(
        route = MainDestinations.AUTH,
    ) {
        val viewModel: AuthViewModel by inject()
        val state by viewModel.state.collectAsState()
        AuthScreen(
            state = state,
            intent = viewModel::triggerIntent,
            appName = stringResource(id = R.string.app_name),
            goHome = goHome
        )
    }
    composable(
        route = MainDestinations.ONBOARDING,
    ) {
        val viewModel: OnBoardingViewModel by inject()
        OnBoardingScreen(
            viewModel = viewModel,
            onOnBoardingFinishedClick = onOnBoardingFinishedClick
        )
    }
}
