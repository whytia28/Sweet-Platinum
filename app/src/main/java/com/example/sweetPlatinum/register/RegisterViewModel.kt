package com.example.sweetPlatinum.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBodyRegister
import com.example.sweetPlatinum.pojo.RegisterResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RegisterViewModel(private val apiService: ApiService) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val registerData = MutableLiveData<Response<RegisterResponse>>()

    fun registerPerson(email: String, username: String, password: String): MutableLiveData<Response<RegisterResponse>> {
        val body = PostBodyRegister(email, password, username)
        disposable.add(
            apiService.registerUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
//                    registerData.postValue(response)
                }, {
//                    registerData.value?.body()?.t = it
                })
        )

        return registerData
    }
}