package com.example.sweetPlatinum.splashScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.repository.SweetRepository

class SplashScreenViewModel(private val repository: SweetRepository) : ViewModel() {

    fun autoLogin(token: String, context: Context) = repository.autoLogin(token, context)
    fun dispose() = repository.dispose()
}