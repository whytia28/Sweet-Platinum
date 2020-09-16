package com.example.sweetPlatinum.splashScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.R

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 6000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val sharedPreferences = getSharedPreferences("MySF", Context.MODE_PRIVATE)

        val contains = sharedPreferences.contains("username")
        if (!contains) {
            val editor = sharedPreferences.edit()
            editor.putString("username", "binarian")
            editor.putString("password", "binar123")
            editor.putString("email", "binarian@binar.co.id")
            editor.apply()
        }


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()

            // close this activity
        }, splashTimeOut)
    }


}
