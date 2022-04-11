package com.adwi.cricket.data

import com.adwi.cricket.data.repository.auth.AuthRepository
import com.adwi.cricket.data.repository.auth.AuthRepositoryImpl
import com.adwi.cricket.data.repository.user.UserRepository
import com.adwi.cricket.data.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
}