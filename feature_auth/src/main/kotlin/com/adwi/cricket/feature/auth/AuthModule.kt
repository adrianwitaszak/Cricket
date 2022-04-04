package com.adwi.cricket.feature.auth

import com.adwi.cricket.feature.auth.ui.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { AuthViewModel(get(), get()) }
}