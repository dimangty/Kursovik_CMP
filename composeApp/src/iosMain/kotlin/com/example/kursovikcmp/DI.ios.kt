package com.example.kursovikcmp

import com.example.kursovikcmp.DB.DatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    single<DatabaseDriverFactory> { DatabaseDriverFactory() }
}
