package com.example.sweetPlatinum.menuActivity

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.room.HistoryDAO
import com.example.sweetPlatinum.room.HistoryDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuViewModel(context: Context): ViewModel() {

    private var historyDAO = HistoryDatabase.getInstance(context)

    fun deleteAllHistory() {
        GlobalScope.launch {
            historyDAO?.historyDAO()?.deleteAll()
        }
    }
}