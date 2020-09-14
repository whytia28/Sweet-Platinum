package com.example.sweetPlatinum.menuActivity.ui.battle

import com.example.sweetPlatinum.network.ApiService

class BattlePresenter(apiService: ApiService) {
    var listener: Listener? = null

    fun goToMultiPlayer(username: String) {
        listener?.goToMultiPlayer(username)
    }

    fun goToSinglePlayer(username: String) {
        listener?.goToSinglePlayer(username)
    }

    interface Listener {
        fun goToMultiPlayer(username: String)
        fun goToSinglePlayer(username: String)
    }
}