package com.qucoon.myhmo.module

import com.qucoon.myhmo.repository.OutsideRepoimpl
import com.qucoon.myhmo.repository.OutsideRepository
import com.qucoon.myhmo.repository.SettingsRepository
import com.qucoon.myhmo.repository.settingsRepoImpl
import org.koin.dsl.module

val repoModule = module {
    single <OutsideRepository>{ OutsideRepoimpl(hmoAPI = get(), paperPrefs = get()) }
    single<SettingsRepository>{ settingsRepoImpl(hmoAPI = get(), paperPrefs = get()) }
}