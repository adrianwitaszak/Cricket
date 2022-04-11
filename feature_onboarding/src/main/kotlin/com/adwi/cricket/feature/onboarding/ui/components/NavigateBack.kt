package com.adwi.cricket.feature.onboarding.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NavigateBack(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isVisible: Boolean,
    icon: ImageVector = Icons.Default.ArrowBack,
    tint: Color = MaterialTheme.colors.onBackground,
    height: Dp = 24.dp,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {
        AnimatedVisibility(visible = isVisible) {
            IconButton(
                onClick = onClick
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Go back",
                    tint = tint
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview(showBackground = true, name = "Light", widthDp = 600)
@Composable
private fun NavigateBackLight() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
        ) {
            NavigateBack(
                onClick = {},
                isVisible = true
            )
        }
    }
}