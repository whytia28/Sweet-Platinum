package com.example.sweetPlatinum.register

import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBodyRegister
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RegisterActivityPresenter(private val apiService: ApiService) {

    private val disposables = CompositeDisposable()
    var listener : Listener? = null

    fun registerPerson(email: String, username: String, password: String) {
        val body = PostBodyRegister(email, password, username)
        disposables.add(
            apiService.registerUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener?.onRegisterSuccess()
                }, {
                    it.message?.let { it1 -> listener?.onRegisterFailure(it1) }
                })
        )
    }

    interface Listener{
        fun onRegisterSuccess()
        fun onRegisterFailure(failureMessage: String)
        fun resetEditText()
    }
}