package com.example.sweetPlatinum.saveBattle

import android.content.Context
import com.example.sweetPlatinum.room.History
import com.example.sweetPlatinum.room.HistoryDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SaveBattlePresenter(context: Context) {

    var listener: Listener? = null

    private var historyDb = HistoryDatabase.getInstance(context)

    fun getListHistory() {
        GlobalScope.launch {
            val listHistory = historyDb?.historyDAO()?.read()
            listHistory?.let {
                listener?.showAllHistory(it)
            }
        }
    }

    fun deleteHistory(history: History) {
        GlobalScope.launch {
            val result = historyDb?.historyDAO()?.delete(history)
            if (result != 0) {
                listener?.onSuccessDelete()
            } else {
                listener?.onFailedDelete()
            }
        }
    }

    interface Listener {
        fun setupUi(listHistory: List<History>)
        fun showAllHistory(listHistory: List<History>)
        fun onSuccessDelete()
        fun onFailedDelete()
    }
}