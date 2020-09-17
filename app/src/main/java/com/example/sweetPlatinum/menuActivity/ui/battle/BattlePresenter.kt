package com.example.sweetPlatinum.menuActivity.ui.battle


class BattlePresenter {
    var listener: Listener? = null


    interface Listener {
        fun goToMultiPlayer(username: String)
        fun goToSinglePlayer(username: String)
    }
}