package com.example.sweetPlatinum.di

import android.content.Context
import com.example.sweetPlatinum.battleActivity.GamePlayPresenter
import com.example.sweetPlatinum.login.LoginActivityPresenter
import com.example.sweetPlatinum.menuActivity.MenuActivityPresenter
import com.example.sweetPlatinum.menuActivity.ui.battle.BattlePresenter
import com.example.sweetPlatinum.menuActivity.ui.history.HistoryPresenter
import com.example.sweetPlatinum.menuActivity.ui.profile.ProfilePresenter
import com.example.sweetPlatinum.register.RegisterActivityPresenter
import com.example.sweetPlatinum.register.RegisterViewModel
import com.example.sweetPlatinum.repository.SweetRepository
import com.example.sweetPlatinum.saveBattle.SaveBattlePresenter
import com.example.sweetPlatinum.splashScreen.SplashScreenPresenter
import com.example.sweetPlatinum.splashScreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {

    factory {
        SweetRepository(get())
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
val repositoryModule = module {
    viewModel { SplashScreenViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}