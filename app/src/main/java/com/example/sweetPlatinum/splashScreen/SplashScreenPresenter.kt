package com.example.sweetPlatinum.splashScreen

import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.AuthResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashScreenPresenter(private val apiService: ApiService) {
    private val disposable = CompositeDisposable()
    var listener: Listener? = null

    fun autoLogin(token: String) {
        disposable.add(
            apiService.autoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener?.goToMenuActivity(it.data)
                }, {
                    it.message?.let { it1 -> listener?.onAuthLoginFailed(it1) }
                    listener?.goToLoginActivity()
                })
        )
    }

    fun dispose() {
        disposable.dispose()
    }

    interface Listener {
        fun goToLandingPage()
        fun goToMenuActivity(data: AuthResponse.Data)
        fun onAuthLoginFailed(errorMessage: String)
        fun goToLoginActivity()
    }
}