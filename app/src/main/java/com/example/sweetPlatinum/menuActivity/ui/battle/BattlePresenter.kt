package com.example.sweetPlatinum.menuActivity.ui.battle

class BattlePresenter {
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