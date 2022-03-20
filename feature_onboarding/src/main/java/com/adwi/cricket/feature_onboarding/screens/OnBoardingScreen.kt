package com.adwi.cricket.feature_onboarding.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adwi.cricket.feature_onboarding.components.NavigateBack

data class OnBoardingQuestion(
    val id: Int,
    val question: String,
    val answers: List<OnBoardingAnswer>,
    val skillLevel: SkillLevel = SkillLevel.LOW
)

enum class SkillLevel {
    LOW, MEDIUM, HIGH
}

data class OnBoardingAnswer(
    val id: String,
    val questionId: Int,
    val answer: String
)

@Composable
fun OnBoardingScreen(
    questions: List<OnBoardingQuestion>
) {
    var currentQuestion by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigateBack(
            onClick = { currentQuestion -= currentQuestion },
            isVisible = currentQuestion > 0
        )
    }
}