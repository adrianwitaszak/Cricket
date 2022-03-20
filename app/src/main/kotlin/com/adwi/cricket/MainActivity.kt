package com.adwi.cricket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.adwi.cricket.feature.auth.ui.AuthViewModel
import com.adwi.cricket.ui.navigation.MainDestinations
import com.adwi.cricket.ui.navigation.cricketNavGraph
import com.adwi.cricket.ui.navigation.rememberMyAppState
import com.adwi.cricket.ui.theme.CricketTheme
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
                    color = MaterialTheme.colorScheme.background) {

                    val appState = rememberMyAppState()

                    AnimatedNavHost(
                        navController = appState.navController,
                        startDestination = MainDestinations.HOME_ROUTE,
                        modifier = Modifier
                    ) {
                        cricketNavGraph(
                            onStartWithoutSignInClick = appState::navigateToHome,
                            onOnBoardingFinishedClick = appState::navigateToHome,
                            onSignOut = appState::navigateToAuth
                        )
                    }
                }
            }
        }
    }
}