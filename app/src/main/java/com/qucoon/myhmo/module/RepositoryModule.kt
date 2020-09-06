package com.qucoon.myhmo.module

import com.qucoon.myhmo.repository.OutsideRepoimpl
import com.qucoon.myhmo.repository.OutsideRepository
import org.koin.dsl.module

val repoModule = module {
    single <OutsideRepository>{ OutsideRepoimpl(hmoAPI = get(), paperPrefs = get()) }

}