package com.example.kursovikcmp

import com.example.kursovikcmp.DI.NetworkModule
import com.example.kursovikcmp.DI.ViewModelsModule
import com.example.kursovikcmp.Network.NetworkSettings
import com.example.kursovikcmp.common.mvvm.LceStateManager
import com.example.kursovikcmp.feature.News.NewsService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val IO_DISPATCHER_NAME = "ioDispatcher"
const val MAIN_DISPATCHER_NAME = "mainDispatcher"
const val DEFAULT_DISPATCHER_NAME = "defaultDispatcher"
const val DEFAULT_SCOPE = "defaultScope"


fun initKoin(
    appModule : Module = module {  }
) = startKoin {
    modules(
        appModule,
        sharedModule
    )
}

inline fun <reified T> getKoinInstance(qualifier: Qualifier? = null) : T {
    return object : KoinComponent {
        val value : T by inject(qualifier)
    }.value
}

val sharedModule: Module
    get() = module {
        includes(commonModule + NetworkCompositeModule + ViewModelsModule.viewModels)
    }

internal val commonModule = module {
    factory { Dispatchers.Default + SupervisorJob() }
    single(named(IO_DISPATCHER_NAME)) { Dispatchers.IO }
    single(named(MAIN_DISPATCHER_NAME)) { Dispatchers.Main }
    single(named(DEFAULT_DISPATCHER_NAME)) { Dispatchers.Default }
    factory(named(DEFAULT_SCOPE)) {
        CoroutineScope(
            get<CoroutineDispatcher>(
                named(DEFAULT_DISPATCHER_NAME)
            )
        )
    }

    factoryOf(::LceStateManager)
    singleOf(::NetworkSettings)
}

internal val NetworkCompositeModule: Module = module {
    includes(NetworkModule.json, NetworkModule.httpClient, NetworkModule.api)
}
