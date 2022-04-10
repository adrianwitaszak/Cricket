package com.adwi.cricket.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.adwi.cricket.components.theme.CricketTheme

@Composable
fun CricketProgressIndicator(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    color: Color = MaterialTheme.colors.primary,
) {
    AnimatedVisibility(visible = isLoading) {
        CircularProgressIndicator(
            color = color,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600, heightDp = 600)
@Composable
private fun ProgressIndicatorLight() {
    CricketTheme(
        darkTheme = false,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            CricketProgressIndicator(isLoading = true)
        }
    }
}