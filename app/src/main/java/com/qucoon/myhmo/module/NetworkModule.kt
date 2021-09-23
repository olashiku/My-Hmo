package com.qucoon.myhmo.module

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.qucoon.myhmo.APIs.HmoAPI
import com.qucoon.myhmo.BuildConfig
import com.qucoon.myhmo.database.PaperPrefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


const val BASEURL = "baseURL would be here"

val networkModule = module {
    single { createWebService<HmoAPI>(RxJava2CallAdapterFactory.create(), BASEURL,paperPrefs = get()) }
}
/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    factory: CallAdapter.Factory, baseUrl: String, paperPrefs: PaperPrefs
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient())
        .build()
    return retrofit.create(T::class.java)
}


fun loggingInterceptor() = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}

fun okHttpClient() =   OkHttpClient.Builder()
    .addInterceptor(headersInterceptor())
    .addInterceptor(loggingInterceptor())
    .readTimeout(5, TimeUnit.MINUTES)
    .connectTimeout(  5, TimeUnit.MINUTES)
    .writeTimeout(5, TimeUnit.MINUTES)
    .build()

fun headersInterceptor() = Interceptor { chain ->
    chain.proceed(chain.request().newBuilder()
        .addHeader("Content-Type", "application/json")
        .build())
}