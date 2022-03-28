package com.adwi.cricket.datasource

import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.repository.UserRepository
import com.adwi.cricket.datasource.repository.UserRepositoryImpl
import com.adwi.cricket.datasource.repository.mapper.UserMapper
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataSourceModule = module {
    single { FirebaseFirestore.getInstance() }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get(), get()) }
    single { Logger(get()) }
    single { UserMapper(get()) }
}