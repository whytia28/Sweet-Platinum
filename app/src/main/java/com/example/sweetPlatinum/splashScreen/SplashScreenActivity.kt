package com.example.sweetPlatinum.splashScreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.login.LoginActivity
import com.example.sweetPlatinum.pojo.AuthResponse
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SplashScreenActivity : AppCompatActivity(), SplashScreenPresenter.Listener {

    private val splashTimeOut: Long = 6000 // 1 sec
    private val disposable = CompositeDisposable()
    private lateinit var sharedPref: SharedPreferences
    private val presenter: SplashScreenPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        presenter.listener = this
        sharedPref = applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            if (sharedPref.contains("token")) {
                val token = MySharedPreferences(this).getData("token").toString()
                presenter.autoLogin(token)
            } else {
                goToLoginActivity()
            }

            // close this activity
        }, splashTimeOut)
    }

    override fun goToLoginActivity() {
        val goToLoginIntent = Intent(this, LoginActivity::class.java)
        startActivity(goToLoginIntent)
        finish()
    }

    override fun goToMenuActivity(data: AuthResponse.Data) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("dataFromAuth", data)
        startActivity(intent)
        finish()
    }

    override fun onAuthLoginFailed(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        MySharedPreferences(this).deleteData()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

}
