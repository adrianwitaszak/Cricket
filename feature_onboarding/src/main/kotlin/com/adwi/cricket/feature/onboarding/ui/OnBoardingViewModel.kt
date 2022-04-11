package com.adwi.cricket.feature.onboarding.ui

import androidx.lifecycle.ViewModel
import com.adwi.cricket.feature.onboarding.model.OnBoardingQuestion
import com.adwi.cricket.feature.onboarding.model.SkillLevel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OnBoardingViewModel() : ViewModel() {

    private var _questions = MutableStateFlow<List<OnBoardingQuestion>>(emptyList())
    val questions: StateFlow<List<OnBoardingQuestion>> get() = _questions

    private var _skillLevel = MutableStateFlow(SkillLevel.LOW)
    val skillLevel: StateFlow<SkillLevel> get() = _skillLevel

    private var _currentQuestion = MutableStateFlow(0)
    val currentQuestion: StateFlow<Int> get() = _currentQuestion

    fun setCurrentQuestion(currentQuestionNumber: Int) {
        _currentQuestion.value = currentQuestionNumber
    }
}