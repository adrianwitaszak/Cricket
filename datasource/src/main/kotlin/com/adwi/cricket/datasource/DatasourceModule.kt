package com.adwi.cricket.datasource

import android.content.Context
import androidx.room.Room
import com.adwi.cricket.datasource.local.dao.UserDao
import com.adwi.cricket.datasource.repository.UserRepository
import com.adwi.cricket.datasource.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

//    @Provides
//    @Singleton
//    fun provideCricketDatabase(@ApplicationContext context: Context): CricketDatabase =
//        Room
//            .databaseBuilder(
//                context,
//                CricketDatabase::class.java,
//                "CRICKET_DATABASE"
//            )
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideUserDao(cricketDatabase: CricketDatabase): UserDao =
//        cricketDatabase.userDao()

    @Provides
    @Singleton
    fun provideUserRepository(
//        database: CricketDatabase
    ): UserRepository = UserRepositoryImpl()
}