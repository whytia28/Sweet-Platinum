package com.example.sweetPlatinum.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sweetPlatinum.BuildConfig
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.AuthResponse
import com.example.sweetPlatinum.room.HistoryDAO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = BuildConfig.BASE_URL

class SweetRepository(private val historyDAO: HistoryDAO) {
    private val disposable = CompositeDisposable()
    private val apiService: ApiService
    var authData = MutableLiveData<AuthResponse>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun autoLogin(token: String, context: Context): LiveData<AuthResponse> {
        disposable.add(
            apiService.autoLogin(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    authData.postValue(it)
                }, {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                })
        )
        return authData
    }

    fun dispose() {
        disposable.dispose()
    }
}