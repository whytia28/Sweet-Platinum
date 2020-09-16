package com.example.sweetPlatinum.menuActivity.ui.history

import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.GetBattleResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HistoryPresenter(private val apiService: ApiService) {

    var listener: Listener? = null
    private val disposable = CompositeDisposable()

    fun getHistory(token: String) {
        listener?.showProgressBar()
        disposable.add(
            apiService.getHistoryBattle(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener?.getHistorySuccess(it.data)
                    listener?.hideProgressBar()
                }, {
                    it.message?.let { it1 -> listener?.getHistoryFailed(it1) }
                })
        )
    }

    interface Listener {
        fun showProgressBar()
        fun hideProgressBar()
        fun setupUi(listHistory: List<GetBattleResponse.Data>)
        fun getHistorySuccess(listHistory: List<GetBattleResponse.Data>)
        fun getHistoryFailed(errorMessage: String)
    }
}