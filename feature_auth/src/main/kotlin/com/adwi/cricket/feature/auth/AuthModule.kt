package com.adwi.cricket.feature.auth

import com.adwi.cricket.feature.auth.ui.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    single { FirebaseAuth.getInstance() }
    viewModel { AuthViewModel(get(), get()) }
}