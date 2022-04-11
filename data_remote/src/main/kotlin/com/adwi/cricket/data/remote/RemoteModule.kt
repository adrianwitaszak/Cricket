package com.adwi.cricket.data.remote

import com.adwi.cricket.data.remote.mapper.UserMapper
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val remoteModule = module {
    single { UserMapper(get()) }
    single { FirebaseFirestore.getInstance() }
    single<RemoteDatasource> { RemoteDatasourceImpl(get(), get(), get()) }
}