package com.example.kursovikcmp

import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

object IosKoin{
    fun initialize() = initKoin(
        appModule =  module {
            sharedModule
        }
    )
}