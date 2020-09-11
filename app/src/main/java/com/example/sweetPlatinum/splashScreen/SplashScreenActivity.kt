package com.example.sweetPlatinum.splashScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetplatinum.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // 1 sec
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


        Glide.with(this)
            .load("https://images.vexels.com/media/users/3/153149/isolated/preview/1dbe90ba2f24289a1668a0dbc8150e2e-gamepad-colored-stroke-icon-by-vexels.png")
            .into(ivMain)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)

            // close this activity
        }, splashTimeOut)
    }


}
