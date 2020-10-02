package com.example.sweetPlatinum.battleActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.network.ApiService
import com.example.sweetPlatinum.pojo.PostBattleBody
import com.example.sweetPlatinum.pojo.PostBattleResponse
import com.example.sweetPlatinum.room.History
import com.example.sweetPlatinum.room.HistoryDAO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class GamePlayViewModel(private val apiService: ApiService, private val historyDAO: HistoryDAO) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val history = MutableLiveData<PostBattleResponse>()
    val scoreBattle : MutableLiveData<Int> = MutableLiveData(0)
    val scoreBattleOpponent : MutableLiveData<Int> = MutableLiveData(0)

    fun saveHistory(token: String, body: PostBattleBody): LiveData<PostBattleResponse> {
        disposable.add(
            apiService.saveHistoryBattle(token, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    history.postValue(it)
                }, {

                })
        )
        return history
    }

    fun saveHistoryLocal(history: History) {
        GlobalScope.launch {
            historyDAO.create(history)
        }
    }

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }

    fun scoreUp() {
        this.scoreBattle.value = this.scoreBattle.value?.plus(10)
    }
    fun scoreUpOpponent() {
        this.scoreBattleOpponent.value = this.scoreBattleOpponent.value?.plus(10)
    }
}