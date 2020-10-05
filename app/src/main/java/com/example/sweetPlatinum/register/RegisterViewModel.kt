package com.example.sweetPlatinum.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBodyRegister
import com.example.sweetPlatinum.pojo.RegisterResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject

class RegisterViewModel(private val apiService: ApiService) : ViewModel() {

    val registerData = MutableLiveData<RegisterResponse>()
    val registerError = MutableLiveData<JSONObject>()
    private val disposable = CompositeDisposable()

    fun registerPerson(
        email: String,
        username: String,
        password: String
    ) {
        val body = PostBodyRegister(email, password, username)
        disposable.add(
            apiService.registerUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code() == 200) {
                        response.body()?.let {
                            registerData.postValue(it)
                        }
                    } else {
                        response.errorBody()?.string()?.let {
                            val jsonObject = JSONObject(it)
                            registerError.postValue(jsonObject)
                        }
                    }
                }, {

                })
        )

    }

}