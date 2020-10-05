package com.example.sweetPlatinum.menuActivity

import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.room.HistoryDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MenuViewModel(private val historyDAO: HistoryDAO): ViewModel() {

    fun deleteAllHistory() {
        GlobalScope.launch {
            historyDAO.deleteAll()
        }
    }
}