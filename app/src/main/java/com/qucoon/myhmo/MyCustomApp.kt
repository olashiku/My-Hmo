package com.qucoon.myhmo

import androidx.multidex.MultiDexApplication
import com.qucoon.myhmo.module.databaseModule
import com.qucoon.myhmo.module.networkModule
import com.qucoon.myhmo.module.repoModule
import com.qucoon.myhmo.module.viewModelModule
import com.zoho.salesiqembed.ZohoSalesIQ
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

        ZohoSalesIQ.init(this, "gEFQUPxGWD9LoTEIvD4%2BkjHRPFecKSdU", "OTUpYCZgJ6Kk%2BdkYpOFVkU7OzBIDxLBvRvI4qQEw%2F42xrzZSkMdMCz7gclK95f9hFSTBhTjC0zr10vIZdPJsa1lfUVXJA7AR%2B0aJ0uR0shHuA1ekB8CQsmRApZi%2FM3OoAT2GHadLgk9eYOROf2s10iPwVNbg7ejK" );

    }
}