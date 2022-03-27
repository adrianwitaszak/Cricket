package com.adwi.cricket.app

import android.app.Application
import com.adwi.cricket.BuildConfig
import com.adwi.cricket.datasource.dataSourceModule
import com.adwi.cricket.feature.auth.ui.authModule
import com.adwi.cricket.feature.home.homeModule
import com.adwi.cricket.feature.onboarding.onBoardingModule
import org.koin.core.context.startKoin
import timber.log.Timber

class CricketApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            modules(
                dataSourceModule,
                authModule,
                homeModule,
                onBoardingModule
            )
        }
    }
}