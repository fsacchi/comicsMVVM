package com.zig.comics

import android.app.Application
import com.zig.presentation.core.injection.presentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initiateKoin()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initiateKoin() {
        startKoin {
            androidContext(this@App)
            modules(provideDependency())
        }
    }

    internal open fun provideDependency() = presentationModules
}
