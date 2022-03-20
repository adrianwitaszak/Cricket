package com.adwi.cricket.feature_onboarding.screens

import androidx.lifecycle.ViewModel
import com.adwi.cricket.feature_onboarding.model.OnBoardingQuestion
import com.adwi.cricket.feature_onboarding.model.SkillLevel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
) : ViewModel() {

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