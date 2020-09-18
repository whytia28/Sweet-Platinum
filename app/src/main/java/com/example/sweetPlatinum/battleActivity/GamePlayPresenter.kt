package com.example.sweetPlatinum.battleActivity

import android.content.Context
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBattleBody
import com.example.sweetPlatinum.room.History
import com.example.sweetPlatinum.room.HistoryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class GamePlayPresenter(context: Context, private val apiService: ApiService) {

    private val disposable = CompositeDisposable()
    private var historyDb = HistoryDatabase.getInstance(context)
    var listener: Listener? = null

    fun saveHistory(token: String, body: PostBattleBody) {
        disposable.add(
            apiService.saveHistoryBattle(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listener?.onSuccessSaveHistory()
                }, {
                    it?.localizedMessage?.let { it1 -> listener?.onFailedSaveHistory(it1) }
                })
        )
    }

    fun saveHistoryLocal(history: History) {
        GlobalScope.launch {
            historyDb?.historyDAO()?.create(history)
        }
    }

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
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