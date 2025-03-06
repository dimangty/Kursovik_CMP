package com.example.kursovikcmp

import com.example.kursovikcmp.DB.DatabaseDriverFactory
import com.example.kursovikcmp.feature.Device.DeviceService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    single<DatabaseDriverFactory> { DatabaseDriverFactory(get()) }
    singleOf(::DeviceService)
}