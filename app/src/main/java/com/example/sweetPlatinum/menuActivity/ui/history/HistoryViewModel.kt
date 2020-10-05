package com.example.sweetPlatinum.menuActivity.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.GetBattleResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HistoryViewModel(private val apiService: ApiService) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val listHistory = MutableLiveData<List<GetBattleResponse.Data>>()

    fun getHistory(token: String): LiveData<List<GetBattleResponse.Data>> {
        disposable.add(
            apiService.getHistoryBattle(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    response.body()?.data.let {
                        if (it != null) {
                            listHistory.postValue(it)
                        }
                    }
                }, {

                })
        )
        return listHistory
    }
}