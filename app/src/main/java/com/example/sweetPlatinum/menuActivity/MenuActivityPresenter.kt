package com.example.sweetPlatinum.menuActivity

import android.content.Context
import com.example.sweetPlatinum.room.HistoryDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuActivityPresenter(context: Context) {

    private val historyDb = HistoryDatabase.getInstance(context)
    var listener: Listener? = null

    fun deleteAllHistory() {
        GlobalScope.launch {
            historyDb?.historyDAO()?.deleteAll()
        }
    }

    interface Listener {
        fun onLogoutSuccess()
    }
}