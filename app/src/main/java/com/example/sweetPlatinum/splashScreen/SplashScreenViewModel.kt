package com.example.sweetPlatinum.splashScreen

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.AuthResponse
import com.example.sweetPlatinum.room.HistoryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class SplashScreenViewModel(private val apiService: ApiService, context: Context) : ViewModel() {

    val loginData = MutableLiveData<AuthResponse>()
    val loginError = MutableLiveData<JSONObject>()
    private val disposable = CompositeDisposable()
    private var historyDb = HistoryDatabase.getInstance(context)

    fun autoLogin(token: String) {
        disposable.add(
            apiService.autoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.code() == 200) {
                        response.body()?.let {
                            loginData.postValue(it)
                        }
                    } else {
                        response.errorBody()?.string()?.let {
                            val jsonObject = JSONObject(it)
                            loginError.postValue(jsonObject)
                        }
                    }
                }, {

                })
        )
    }

    fun deleteAllHistory() {
        GlobalScope.launch {
            historyDb?.historyDAO()?.deleteAll()
        }
    }
}