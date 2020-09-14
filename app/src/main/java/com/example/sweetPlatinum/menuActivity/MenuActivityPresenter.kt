package com.example.sweetPlatinum.menuActivity

import com.example.sweetPlatinum.network.ApiService

class MenuActivityPresenter(apiService: ApiService) {

    var listener: Listener? = null

    interface Listener {
        fun onLogoutSuccess()
    }
}