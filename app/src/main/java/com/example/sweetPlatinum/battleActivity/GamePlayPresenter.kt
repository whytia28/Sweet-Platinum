package com.example.sweetPlatinum.battleActivity

import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBattleBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GamePlayPresenter(private val apiService: ApiService) {

    private val disposable = CompositeDisposable()
    var listener: Listener? = null

    fun saveHistory(token: String, body: PostBattleBody) {
        disposable.add(
            apiService.saveHistoryBattle(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                })
        )
    }

    fun dispose() {
        disposable.dispose()
    }

    interface Listener {
        fun startNew()
        fun setOverlay()
        fun showResult()
        fun setCpuOverlay()
        fun onSuccessSaveHistory()
        fun onFailedSaveHistory(errorMessage: String)
        fun shareTo()
        fun showButtonShare()
    }
}