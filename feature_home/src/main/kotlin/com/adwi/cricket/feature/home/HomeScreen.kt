package com.adwi.cricket.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.adwi.cricket.datasource.logger.Logger
import org.koin.androidx.compose.get
import org.koin.androidx.compose.inject

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = get(),
    onSignOutClick: () -> Unit,
) {

    val logger: Logger by inject()

    Column {
        Text(text = "Home screen")
        Button(onClick = onSignOutClick) {
            Text(text = "Sign out")
        }
        Button(onClick = {
            logger.logErrorToCrashlytics(
                "Test Crash",
                "user_id",
                "Adrian",

            )
            throw RuntimeException("Test Crash")
        }) {
            Text(text = "Crash now")
        }
    }

}