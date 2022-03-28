package com.adwi.cricket.feature.auth.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel { AuthViewModel(get(), get()) }
}