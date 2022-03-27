package com.adwi.cricket.datasource

import com.adwi.cricket.datasource.repository.UserRepository
import com.adwi.cricket.datasource.repository.UserRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {


    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebase(): Firebase = Firebase

    @Provides
    @Singleton
    fun provideCloudFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideUserRepository(
        firebaseAuth: FirebaseAuth,
    ): UserRepository =
        UserRepositoryImpl(firebaseAuth)
}