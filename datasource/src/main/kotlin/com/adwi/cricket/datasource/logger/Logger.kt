package com.adwi.cricket.datasource.logger

import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class Logger(
    private val crashlytics: FirebaseCrashlytics,
) {
    fun logErrorToCrashlytics(
        message: String,
        key: String = "userId",
        keyValue: String,
        exception: Exception = Exception("Exception"),
    ) {
        log(message)
        crashlytics.log(message)
        crashlytics.setCustomKey(key, keyValue)
        crashlytics.recordException(exception)
    }

    fun log(message: String, tag: String = this.javaClass.name) {
        Timber.tag(tag).e(message)
    }

    fun setUserId(userId: String) {
        crashlytics.setUserId(userId)
    }
}