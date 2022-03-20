package com.adwi.cricket.data.repository

import com.adwi.cricket.data.local.CricketDatabase
import com.adwi.cricket.data.repository.repository.UserRepository
import com.adwi.cricket.data.repository.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(database: CricketDatabase): UserRepository =
        UserRepositoryImpl(database)
}