package com.example.sweetPlatinum.battleActivity

import androidx.lifecycle.ViewModel
import com.example.sweetPlatinum.pojo.PostBattleBody
import com.example.sweetPlatinum.repository.SweetRepository
import com.example.sweetPlatinum.room.History
import java.text.SimpleDateFormat
import java.util.*

class GamePlayViewModel(private val repository: SweetRepository) : ViewModel() {

    fun saveHistory(token: String, body: PostBattleBody) = repository.saveHistory(token, body)
    fun saveHistoryLocal(history: History) = repository.saveHistoryLocal(history)

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }
}