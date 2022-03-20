package com.adwi.cricket.feature.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun NavigateNext(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isVisible: Boolean,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        AnimatedVisibility(
            visible = isVisible,
        ) {
            Button(onClick = onClick) {
                Text(
                    text = "Next",
                    modifier = modifier.padding(12.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600)
@Composable
private fun NavigateNextLight() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
        ) {
            NavigateNext(
                onClick = {},
                isVisible = true
            )
        }
    }
}