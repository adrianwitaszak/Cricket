package com.adwi.cricket.feature_onboarding.model

data class OnBoardingQuestion(
    val id: Int,
    val question: String,
    val answers: List<OnBoardingAnswer>,
    val skillLevel: SkillLevel = SkillLevel.LOW
)

enum class SkillLevel {
    LOW, MEDIUM, HIGH
}

