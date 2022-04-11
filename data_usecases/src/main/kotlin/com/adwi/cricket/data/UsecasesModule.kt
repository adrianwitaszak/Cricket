package com.adwi.cricket.data

import com.adwi.cricket.data.usecases.GetCurrentUser
import com.adwi.cricket.data.usecases.SignInWithGoogle
import com.adwi.cricket.data.usecases.SignOut
import org.koin.dsl.module

val usecasesModule = module {
    single { GetCurrentUser(get(), get()) }
    single { SignInWithGoogle(get(), get()) }
    single { SignOut(get()) }
}