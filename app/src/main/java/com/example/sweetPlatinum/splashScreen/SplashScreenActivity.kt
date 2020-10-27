package com.example.sweetPlatinum.splashScreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.landingPage.LandingActivity
import com.example.sweetPlatinum.login.LoginActivity
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.pojo.AuthResponse
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 6000 // 2
    private lateinit var sharedPref: SharedPreferences


    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SplashTheme)
        setContentView(R.layout.activity_splash_screen)


        sharedPref = applicationContext.getSharedPreferences("userData", Context.MODE_PRIVATE)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            if (sharedPref.contains("token")) {
                autoLogin()
            } else {
                goToLandingPage()
            }

            // close this activity
        }, splashTimeOut)
    }

    private fun autoLogin() {
        val token = MySharedPreferences(this).getData("token").toString()
        viewModel.autoLogin(token)
        viewModel.loginData.observe(this, {
            it.data?.let { it1 -> goToMenuActivity(it1) }
        })
        viewModel.loginError.observe(this, {
            goToLoginActivity()
            viewModel.deleteAllHistory()
            onAuthLoginFailed(it.getString("errors"))
        })
    }

    private fun goToLandingPage() {
        val goToLandingIntent = Intent(this, LandingActivity::class.java)
        startActivity(goToLandingIntent)
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
        finish()
    }

    private fun goToMenuActivity(data: AuthResponse.Data) {
        val intent = Intent(this, MenuActivity::class.java)
        intent.putExtra("dataFromAuth", data)
        startActivity(intent)
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
        finish()
    }

    private fun onAuthLoginFailed(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        MySharedPreferences(this).deleteData()
    }

    private fun goToLoginActivity() {
        val loginIntent = Intent(this, LoginActivity::class.java)
        startActivity(loginIntent)
        overridePendingTransition(R.anim.from_right, R.anim.to_left)
        finish()
    }

}
