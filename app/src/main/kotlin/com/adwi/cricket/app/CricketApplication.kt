package com.adwi.cricket.app

import android.app.Application
import com.adwi.cricket.BuildConfig
import com.adwi.cricket.datasource.dataSourceModule
import com.adwi.cricket.feature.auth.authModule
import com.adwi.cricket.feature.home.homeModule
import com.adwi.cricket.feature.onboarding.onBoardingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class CricketApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@CricketApplication)
            modules(
                appModule,
                dataSourceModule,
                authModule,
                homeModule,
                onBoardingModule
            )
        }
    }
}