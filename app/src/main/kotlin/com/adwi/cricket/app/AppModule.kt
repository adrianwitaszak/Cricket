package com.adwi.cricket.app

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val appModule = module {

    single { Firebase }
    single { FirebaseAuth.getInstance() }
    single { FirebaseCrashlytics.getInstance() }
}