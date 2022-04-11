package com.adwi.cricket.core.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.android.BuildConfig
import timber.log.Timber

enum class LogType { Verbose, Debug, Info, Error }

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
        logType: LogType = LogType.Error,
    ) {
        if (isDebug) {
            logToConsole(message, tag, logType)
        } else {
            logToCrashlytics(message, key, keyValue, exception)
        }
    }

    private fun logToConsole(
        message: String,
        tag: String,
        logType: LogType,
    ) {
        when (logType) {
            LogType.Verbose -> Timber.tag(tag).v(message)
            LogType.Debug -> Timber.tag(tag).d(message)
            LogType.Info -> Timber.tag(tag).i(message)
            LogType.Error -> Timber.tag(tag).e(message)
        }

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