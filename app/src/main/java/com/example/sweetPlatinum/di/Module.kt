package com.example.sweetPlatinum.di

import com.example.sweetPlatinum.BaseApp
import com.example.sweetPlatinum.BuildConfig
import com.example.sweetPlatinum.battleActivity.GamePlayViewModel
import com.example.sweetPlatinum.login.LoginViewModel
import com.example.sweetPlatinum.menuActivity.MenuViewModel
import com.example.sweetPlatinum.menuActivity.ui.history.HistoryViewModel
import com.example.sweetPlatinum.menuActivity.ui.profile.ProfileViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.register.RegisterViewModel
import com.example.sweetPlatinum.saveBattle.SaveBattleViewModel
import com.example.sweetPlatinum.splashScreen.SplashScreenViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = BuildConfig.BASE_URL
val appModule = module {

//    single {
//        BaseApp().applicationContext
//    }

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
}

val viewModule = module {
    viewModel { SplashScreenViewModel(get(), androidContext()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { MenuViewModel(androidContext()) }
    viewModel { GamePlayViewModel(get(), androidContext()) }
    viewModel { SaveBattleViewModel(androidContext()) }

}