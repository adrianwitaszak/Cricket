package com.adwi.cricket.datasource

import com.adwi.cricket.datasource.repository.UserRepository
import com.adwi.cricket.datasource.repository.UserRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val dataSourceModule = module {
    single { Firebase }
    single { Firebase.firestore }
    single { FirebaseAuth.getInstance() }
    single<UserRepository> { UserRepositoryImpl(get()) }
}