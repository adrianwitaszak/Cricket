package com.adwi.cricket

import android.app.Application
import com.adwi.cricket.feature.auth.authModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class CricketApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin{
            androidLogger()
            androidContext(this@CricketApplication)
            modules(authModule)
        }
    }
}