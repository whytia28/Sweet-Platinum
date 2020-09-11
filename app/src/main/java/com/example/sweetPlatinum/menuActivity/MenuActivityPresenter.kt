package com.example.sweetPlatinum.menuActivity

class MenuActivityPresenter {

    var listener: Listener? = null

    fun onLogoutSuccess() {
        listener?.onLogoutSuccess()
    }

    interface Listener {
        fun onLogoutSuccess()
    }
}