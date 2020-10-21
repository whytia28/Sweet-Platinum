package com.example.sweetPlatinum.landingPage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.sweetPlatinum.R

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SplashTheme)
        setContentView(R.layout.activity_landing)

        val host = NavHostFragment.create(R.navigation.landing_nav_graph)

        supportFragmentManager.beginTransaction().replace(R.id.activity_landing, host)
            .setPrimaryNavigationFragment(host).commit()

    }
}