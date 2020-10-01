package com.example.sweetPlatinum

import android.app.Application
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.dbModule
import com.example.sweetPlatinum.di.repositoryModule
import com.example.sweetPlatinum.di.viewModule
import org.koin.android.ext.android.startKoin


class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            this, listOf(appModule, dbModule, repositoryModule, viewModule)
        )
    }
}