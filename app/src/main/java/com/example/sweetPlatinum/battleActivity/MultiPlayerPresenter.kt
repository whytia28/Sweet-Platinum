package com.example.sweetPlatinum.battleActivity

import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBattleBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MultiPlayerPresenter(private val apiService: ApiService) {

    var listener: Listener? = null
    private val disposables = CompositeDisposable()

    fun saveHistory(token: String, body: PostBattleBody) {
        disposables.add(
            apiService.saveHistoryBattle(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener?.onSuccessSaveHistory()
                }, {
                    it.message?.let { it1 -> listener?.onFailedSaveHistory(it1) }
                })
        )

    }
    fun dispose() {
        disposables.dispose()
    }

    interface Listener {
        fun startNew()
        fun showResult()
        fun setOverlay()
        fun onSuccessSaveHistory()
        fun onFailedSaveHistory(errorMessage: String)
        fun shareTo()
    }
}