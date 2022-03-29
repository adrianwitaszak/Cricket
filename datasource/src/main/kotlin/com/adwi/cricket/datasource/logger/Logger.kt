package com.adwi.cricket.datasource.logger

import com.adwi.cricket.datasource.BuildConfig
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class Logger(
    private val crashlytics: FirebaseCrashlytics,
) {
    private val isDebug = BuildConfig.DEBUG

    fun log(
        message: String,
        tag: String = this.javaClass.name,
        key: String = "userId",
        keyValue: String = "",
        exception: Exception = Exception("Exception"),
    ) {
        if (isDebug) {
            logToConsole(message, tag)
        } else {
            logToCrashlytics(message, key, keyValue, exception)
        }
    }

    private fun logToConsole(message: String, tag: String = this.javaClass.name) {
        Timber.tag(tag).e(message)
    }

    private fun logToCrashlytics(
        message: String,
        key: String = "userId",
        keyValue: String = "",
        exception: Exception = Exception("Exception"),
    ) {
        crashlytics.log(message)
        crashlytics.setCustomKey(key, keyValue)
        crashlytics.recordException(exception)
    }

    fun setUserId(userId: String) {
        if (!isDebug) {
            crashlytics.setUserId(userId)
        }
    }
}