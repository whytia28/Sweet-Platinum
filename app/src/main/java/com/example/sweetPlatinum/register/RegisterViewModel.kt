package com.example.sweetPlatinum.register

import android.widget.Toast
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
    private val disposable = CompositeDisposable()
    val registerDataError = MutableLiveData<String>()
    val registerDataSuccess = MutableLiveData<RegisterResponse>()
    val throwable = MutableLiveData<Throwable>()

    fun registerPerson(email: String, username: String, password: String){
        val body = PostBodyRegister(email, password, username)
        disposable.add(
            apiService.registerUser(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code() == 422) {
                        response.errorBody()?.string()?.let {
                            val jsonObject = JSONObject(it)
                            registerDataError.postValue(jsonObject.getString("errors"))
                        }
                    } else {
                        registerDataSuccess.postValue(response.body())
                    }
                }, {
                    throwable.postValue(it)
                })
        )
    }
}