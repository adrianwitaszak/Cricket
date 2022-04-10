package com.adwi.cricket.datasource

import com.adwi.cricket.datasource.logger.Logger
import com.adwi.cricket.datasource.mapper.UserMapper
import com.adwi.cricket.datasource.remote.RemoteDatasource
import com.adwi.cricket.datasource.remote.RemoteDatasourceImpl
import com.adwi.cricket.datasource.repository.auth.AuthRepository
import com.adwi.cricket.datasource.repository.auth.AuthRepositoryImpl
import com.adwi.cricket.datasource.repository.user.UserRepository
import com.adwi.cricket.datasource.repository.user.UserRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataSourceModule = module {
    single { FirebaseFirestore.getInstance() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
    single<RemoteDatasource> { RemoteDatasourceImpl(get(), get()) }
    single { Logger(get()) }
    single { UserMapper(get()) }
}