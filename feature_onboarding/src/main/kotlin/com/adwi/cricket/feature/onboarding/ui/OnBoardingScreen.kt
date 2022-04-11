package com.adwi.cricket.feature.onboarding.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.adwi.cricket.feature.onboarding.ui.components.NavigateBack
import com.adwi.cricket.feature.onboarding.ui.components.NavigateNext
import org.koin.androidx.compose.get

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = get(),
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