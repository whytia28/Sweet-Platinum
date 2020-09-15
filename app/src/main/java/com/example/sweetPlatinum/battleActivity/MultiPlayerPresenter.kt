package com.example.sweetPlatinum.battleActivity

import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBattleBody
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MultiPlayerPresenter(private val apiService: ApiService) {

    private lateinit var disposable: Disposable
    var listener: Listener? = null

    fun saveHistory(token: String, body: PostBattleBody) {
        apiService.saveHistoryBattle(token, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                listener?.onSuccessSaveHistory()
            }, {
                listener?.onFailedSaveHistory(it.localizedMessage)
            })

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