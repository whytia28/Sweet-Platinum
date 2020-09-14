package com.example.sweetPlatinum

import android.app.Application
import com.example.sweetPlatinum.di.appModule
import org.koin.core.context.startKoin

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}