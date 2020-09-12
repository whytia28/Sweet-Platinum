package com.example.sweetPlatinum.menuActivity.ui.profile

class ProfilePresenter {

    var listener: Listener? = null

    interface Listener {
        fun showEditUi()
        fun showSetupUi()
        fun showProgressBar()
        fun hiddenProgressBar()
        fun onUpdateSuccess()
        fun showProfile(username: String, email: String, photo: String)
        fun showProfileFailed(errorMessage: String)
        fun onUpdateFailed(errorMessage: String)
    }
}