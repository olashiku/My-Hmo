package com.qucoon.myhmo.module

import com.qucoon.myhmo.repository.OutsideRepository
import com.qucoon.myhmo.viewmodel.OutsideViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel{ OutsideViewModel(outsideRepository = get(), paperPrefs = get()) }

}