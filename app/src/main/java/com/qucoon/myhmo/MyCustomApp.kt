package com.qucoon.myhmo

import androidx.multidex.MultiDexApplication
import com.qucoon.myhmo.module.databaseModule
import com.qucoon.myhmo.module.networkModule
import com.qucoon.myhmo.module.repoModule
import com.qucoon.myhmo.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyCustomApp: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyCustomApp)
            modules(listOf(repoModule, viewModelModule,
                databaseModule, networkModule))
        }
    }
}