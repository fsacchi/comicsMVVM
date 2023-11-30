package com.zig.data_remote.core.injection

import com.google.gson.GsonBuilder
import com.zig.data.repository.ComicsRepository
import com.zig.data_remote.BuildConfig
import com.zig.data_remote.repository.ComicsRemoteRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

val dataRemoteModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").i(message) }
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }  else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        httpLoggingInterceptor
    }

    single {
        GsonConverterFactory.create(
            GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create()
        )
    }

    single<ComicsRepository> { ComicsRemoteRepository(get()) }
}
