package com.example.sweetPlatinum.login

import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.LoginResponse
import com.example.sweetPlatinum.pojo.PostLoginBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginActivityPresenter(private val apiService: ApiService) {

    var listener: Listener? = null
    private val disposables = CompositeDisposable()

    fun loginPerson(email: String, password: String, rememberMe: Boolean) {
        listener?.showProgressBar()
        val body = PostLoginBody(email, password)
        disposables.add(
            apiService.validateLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener?.hideProgressBar()
                    listener?.goToMenuActivity(it.data)
                    listener?.onLoginSuccess()
                    if (rememberMe) {
                        listener?.saveToken("token", "Bearer ${it.data.token}")
                    }
                }, {
                    it.message?.let { it1 -> listener?.onLoginFailure(it1) }
                })
        )
    }
    fun dispose() {
        disposables.dispose()
    }

    interface Listener {
        fun onLoginSuccess()
        fun onLoginFailure(failureMessage: String)
        fun goToRegister()
        fun goToMenuActivity(data: LoginResponse.Data)
        fun showProgressBar()
        fun hideProgressBar()
        fun saveToken(key: String, data: String)
        fun resetEditText()
    }

}