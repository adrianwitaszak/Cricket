package com.adwi.cricket.feature_onboarding.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.adwi.cricket.feature_onboarding.components.NavigateBack
import com.adwi.cricket.feature_onboarding.components.NavigateNext

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel,
    onOnBoardingFinishedClick: () -> Unit,
) {
    val questions by viewModel.questions.collectAsState()
    val currentQuestion by viewModel.currentQuestion.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigateBack(
            onClick = { viewModel.setCurrentQuestion(currentQuestion - 1) },
            isVisible = currentQuestion > 0
        )
        NavigateNext(
            onClick = {
                if (currentQuestion == questions.size)
                    onOnBoardingFinishedClick()
                else
                    viewModel.setCurrentQuestion(currentQuestion + 1)
            },
            isVisible = currentQuestion <= questions.size
        )
    }
}