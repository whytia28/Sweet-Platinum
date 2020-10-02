package com.example.sweetPlatinum.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.LoginResponse
import com.example.sweetPlatinum.pojo.PostLoginBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.json.JSONObject


class LoginViewModel(private val apiService: ApiService) : ViewModel() {
    val loginData = MutableLiveData<LoginResponse>()
    val errorData = MutableLiveData<JSONObject>()
    private val disposable = CompositeDisposable()

    fun loginPerson(
        email: String,
        password: String,
        rememberMe: Boolean
    ) {
        val body = PostLoginBody(email, password)
        disposable.add(
            apiService.validateLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code() == 200) {
                        response.body()?.let {
                            if (rememberMe) {
                                loginData.postValue(it)
                            }
                        }
                    } else {
                        response.errorBody()?.string()?.let {
                            val jsonObject = JSONObject(it)
                            errorData.postValue(jsonObject)
                        }
                    }
                }, {
                    loginData.postValue(LoginResponse(it))
                })
        )
    }
}