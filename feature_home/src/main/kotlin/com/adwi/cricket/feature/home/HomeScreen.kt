package com.adwi.cricket.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onSignOutClick: () -> Unit
) {

    Column {
        Text(text = "Home screen")
        Button(onClick = onSignOutClick) {
            Text(text = "Sign out")
        }
    }

}