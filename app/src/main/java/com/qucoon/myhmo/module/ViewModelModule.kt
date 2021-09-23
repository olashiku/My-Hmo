package com.qucoon.myhmo.module

import com.qucoon.myhmo.livedata.DataPasserLiveData
import com.qucoon.myhmo.repository.OutsideRepository
import com.qucoon.myhmo.viewmodel.DashboardViewModel
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import com.qucoon.myhmo.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel{ OutsideViewModel(outsideRepository = get(), paperPrefs = get()) }
    viewModel { SettingsViewModel(settingsRepository = get(), paperPrefs = get()) }
    viewModel { DashboardViewModel(dashboardRepossitory = get(), paperPrefs = get()) }
    single { DataPasserLiveData() }




}