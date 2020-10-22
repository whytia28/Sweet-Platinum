package com.example.sweetPlatinum

import android.app.Application
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(appModule, viewModule)
        }
    }
}