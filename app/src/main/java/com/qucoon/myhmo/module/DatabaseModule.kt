package com.qucoon.myhmo.module

import androidx.room.Room
import com.qucoon.myhmo.database.PaperPrefs
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { PaperPrefs(androidApplication()) }
    //Room Database instance
//    single {
//        Room.databaseBuilder(androidApplication(), NeptuneRoomDatabase::class.java, "neptune-db")
//            .fallbackToDestructiveMigration()
//            .build()
//    }

    //Dao
   // val createAtStart = false
//    single(createdAtStart = createAtStart) { get<NeptuneRoomDatabase>().accountsDao() }
//    single(createdAtStart = createAtStart) { get<NeptuneRoomDatabase>().fullBankListDao() }
}