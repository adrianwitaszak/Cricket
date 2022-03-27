package com.adwi.cricket.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.adwi.cricket.app.ui.navigation.MainDestinations
import com.adwi.cricket.app.ui.navigation.cricketNavGraph
import com.adwi.cricket.app.ui.navigation.rememberMyAppState
import com.adwi.cricket.app.ui.theme.CricketTheme
import com.adwi.cricket.feature.auth.ui.AuthViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalAnimationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CricketTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val appState = rememberMyAppState()

                    AnimatedNavHost(
                        navController = appState.navController,
                        startDestination = MainDestinations.AUTH
                    ) {
                        cricketNavGraph(
                            onOnBoardingFinishedClick = appState::navigateToHome,
                            onSignOutClick = {
                                authViewModel.signOut()
                                appState.navigateToAuth()
                            },
                            goHome = appState::navigateToHome
                        )
                    }
                }
            }
        }
    }
}