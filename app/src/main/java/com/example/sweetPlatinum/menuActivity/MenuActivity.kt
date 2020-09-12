package com.example.sweetPlatinum.menuActivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sweetPlatinum.battleActivity.MultiPlayerActivity
import com.example.sweetPlatinum.menuActivity.ui.battle.BattleFragment
import com.example.sweetPlatinum.setting.SettingActivity
import com.example.sweetplatinum.R


class MenuActivity : AppCompatActivity(), MenuActivityPresenter.Listener {

    lateinit var menuActivityPresenter: MenuActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
//        setSupportActionBar(menu_actionbar)
        menuActivityPresenter = MenuActivityPresenter()
        menuActivityPresenter.listener = this

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_battle, R.id.navigation_history, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                //            val loginIntent = Intent(this, LoginActivity::class.java)
//                MySharedPreferences(this).deleteData()
//            startActivity(loginIntent)
                onLogoutSuccess()
            }

            R.id.setting -> {
                val settingIntent = Intent(this, SettingActivity::class.java)
                startActivity(settingIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onLogoutSuccess() {
        Toast.makeText(this, R.string.logout_success, Toast.LENGTH_SHORT).show()
        finish()
    }

}