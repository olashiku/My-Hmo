package com.qucoon.myhmo.module

import com.qucoon.myhmo.repository.*
import org.koin.dsl.module

val repoModule = module {
    single <OutsideRepository>{ OutsideRepoimpl(hmoAPI = get(), paperPrefs = get()) }
    single<SettingsRepository>{ settingsRepoImpl(hmoAPI = get(), paperPrefs = get()) }
    single<DashboardRepossitory>{ implDashboardRepossitory(hmoAPI = get(), paperPrefs = get()) }

}