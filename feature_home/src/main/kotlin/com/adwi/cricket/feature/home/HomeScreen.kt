package com.adwi.cricket.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.get

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = get(),
    onSignOutClick: () -> Unit,
) {
    Column {
        Text(text = "Home screen")
        Button(onClick = onSignOutClick) {
            Text(text = "Sign out")
        }
    }

}