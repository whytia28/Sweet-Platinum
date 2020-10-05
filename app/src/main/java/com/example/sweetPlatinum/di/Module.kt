package com.example.sweetPlatinum.di

import android.content.Context
import com.example.sweetPlatinum.battleActivity.GamePlayViewModel
import com.example.sweetPlatinum.login.LoginViewModel
import com.example.sweetPlatinum.menuActivity.MenuViewModel
import com.example.sweetPlatinum.menuActivity.ui.history.HistoryViewModel
import com.example.sweetPlatinum.menuActivity.ui.profile.ProfileViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.register.RegisterViewModel
import com.example.sweetPlatinum.room.HistoryDatabase
import com.example.sweetPlatinum.saveBattle.SaveBattlePresenter
import com.example.sweetPlatinum.splashScreen.SplashScreenViewModel
import com.example.sweetPlatinum.BuildConfig
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = BuildConfig.BASE_URL
val appModule = module {

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
        SaveBattlePresenter(context)
    }
}

val dbModule = module {
    factory { HistoryDatabase.getInstance(context = get())!! }
    factory { get<HistoryDatabase>().historyDAO() }
}

val viewModule = module {
    viewModel { SplashScreenViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { MenuViewModel(get()) }
    viewModel { GamePlayViewModel(get(), get()) }
}