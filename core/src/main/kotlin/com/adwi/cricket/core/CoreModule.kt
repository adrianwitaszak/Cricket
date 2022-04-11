package com.adwi.cricket.core

import com.adwi.cricket.core.logger.Logger
import org.koin.dsl.module

val coreModule = module {
    single { Logger(get()) }
}