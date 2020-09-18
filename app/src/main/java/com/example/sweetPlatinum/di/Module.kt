package com.example.sweetPlatinum.di

import android.content.Context
import com.example.sweetPlatinum.menuActivity.MenuActivityPresenter
import com.example.sweetPlatinum.menuActivity.ui.battle.BattlePresenter
import com.example.sweetPlatinum.menuActivity.ui.profile.ProfilePresenter
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.BuildConfig
import com.example.sweetPlatinum.battleActivity.GamePlayPresenter
import com.example.sweetPlatinum.login.LoginActivityPresenter
import com.example.sweetPlatinum.menuActivity.ui.history.HistoryPresenter
import com.example.sweetPlatinum.register.RegisterActivityPresenter
import com.example.sweetPlatinum.saveBattle.SaveBattlePresenter
import com.example.sweetPlatinum.splashScreen.SplashScreenPresenter
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = BuildConfig.BASE_URL
val appModule: Module = module {

    single {
        OkHttpClient.Builder()
            .build()
    }

    factory<ApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }

    factory { (context: Context) ->
        MenuActivityPresenter(context)
    }
    factory {
        BattlePresenter()
    }
    factory {
        ProfilePresenter(get())
    }
    factory { (context: Context) ->
        GamePlayPresenter(context, get())
    }
    factory {
        SplashScreenPresenter(get())
    }
    factory {
        LoginActivityPresenter(get())
    }
    factory {
        RegisterActivityPresenter(get())
    }
    factory {
        HistoryPresenter(get())
    }
    factory { (context: Context) ->
        SaveBattlePresenter(context)
    }
}