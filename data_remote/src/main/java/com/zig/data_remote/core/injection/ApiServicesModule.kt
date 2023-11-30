package com.zig.data_remote.core.injection

import com.zig.data_remote.BuildConfig
import com.zig.data_remote.services.ComicsServices
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val servicesModule = module {

    single(named("base_url")) {
        BuildConfig.API_URL
    }

    single {
        createService<ComicsServices>(get(named("base_url")), get(), get())
    }
}

inline fun <reified T> createService(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    gson: GsonConverterFactory
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gson).build()
    return retrofit.create(T::class.java)
}
