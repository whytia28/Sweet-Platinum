package com.example.sweetPlatinum.saveBattle

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.room.History
import com.example.sweetPlatinum.room.HistoryDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SaveBattleViewModel(context: Context) : ViewModel() {

    private var historyDb = HistoryDatabase.getInstance(context)
    private val listHistory = MutableLiveData<List<History>>()
    private val updateList = MutableLiveData<Int>()

    fun getListHistory(): MutableLiveData<List<History>> {
        GlobalScope.launch {
            listHistory.postValue(historyDb?.historyDAO()?.read())
        }
        return listHistory
    }

    fun deleteHistory(history: History): MutableLiveData<Int> {
        GlobalScope.launch {
            updateList.postValue(historyDb?.historyDAO()?.delete(history))
        }
        return updateList
    }

    fun getUpdateList(): MutableLiveData<Int> {
        return updateList
    }

}