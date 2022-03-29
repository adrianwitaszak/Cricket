package com.adwi.cricket.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.adwi.cricket.app.navigation.MainDestinations
import com.adwi.cricket.app.navigation.cricketNavGraph
import com.adwi.cricket.app.navigation.rememberMyAppState
import com.adwi.cricket.components.theme.CricketTheme
import com.adwi.cricket.feature.auth.ui.AuthViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import org.koin.androidx.compose.inject

@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CricketTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    val authViewModel: AuthViewModel by inject()
                    val appState = rememberMyAppState()
                    val user by authViewModel.user.collectAsState()

                    AnimatedNavHost(
                        navController = appState.navController,
                        startDestination = if (user == null)
                            MainDestinations.AUTH else MainDestinations.HOME
                    ) {
                        cricketNavGraph(
                            onOnBoardingFinishedClick = appState::navigateToHome,
                            onSignOutClick = {
                                authViewModel.signOut()
                                appState.navigateToAuth()
                            },
                            goHome = { appState.navigateToHome(true) }
                        )
                    }
                }
            }
        }
    }
}