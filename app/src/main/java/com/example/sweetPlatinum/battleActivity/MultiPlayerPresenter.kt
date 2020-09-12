package com.example.sweetPlatinum.battleActivity

class MultiPlayerPresenter {

    var listener: Listener? = null

    interface Listener {
        fun startNew()
        fun showResult()
        fun setOverlay()
        fun onSuccessSaveHistory()
        fun onFailedSaveHistory()
    }
}