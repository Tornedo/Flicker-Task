package com.coding.flickertask.di.module

import com.coding.flickertask.data.repository.LocalStorageRepository
import org.koin.dsl.module

val dataStoreModule = module {
    single { LocalStorageRepository(get()) }
}