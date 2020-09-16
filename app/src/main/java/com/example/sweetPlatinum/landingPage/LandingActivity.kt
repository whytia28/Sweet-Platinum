package com.example.sweetPlatinum.landingPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.sweetPlatinum.*
import com.example.sweetPlatinum.login.LoginActivity

class LandingActivity : AppCompatActivity(), FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        openFirstFragment()
    }

    private fun commitFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }


    override fun openFirstFragment() {
        val objectFirstFragment = FirstFragment()
        objectFirstFragment.setListener(this)
        commitFragment(objectFirstFragment)
    }

    override fun openSecondFragment() {
        val objectSecondFragment = SecondFragment()
        objectSecondFragment.setListener(this)
        commitFragment(objectSecondFragment)
    }

    override fun openThirdFragment() {
        val objectThirdFragment = ThirdFragment()
        objectThirdFragment.setListener(this)
        commitFragment(objectThirdFragment)
    }

    override fun goToLoginActivity() {
        val intentKeLoginActivity = Intent(this, LoginActivity::class.java)
        startActivity(intentKeLoginActivity)
        finish()
    }
}