package com.adwi.cricket.feature.onboarding

import com.adwi.cricket.feature.onboarding.ui.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val onBoardingModule = module {
    viewModel { OnBoardingViewModel() }
}