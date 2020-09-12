package com.example.sweetPlatinum.menuActivity

class MenuActivityPresenter {

    var listener: Listener? = null

    interface Listener {
        fun onLogoutSuccess()
    }
}