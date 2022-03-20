package com.adwi.cricket.data.local

import android.content.Context
import androidx.room.Room
import com.adwi.cricket.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun provideCricketDatabase(@ApplicationContext context: Context): CricketDatabase =
        Room
            .databaseBuilder(
                context,
                CricketDatabase::class.java,
                "cricket_database"
            )
            .build()

    @Provides
    @Singleton
    fun provideUserDao(cricketDatabase: CricketDatabase): UserDao =
        cricketDatabase.userDao()
}