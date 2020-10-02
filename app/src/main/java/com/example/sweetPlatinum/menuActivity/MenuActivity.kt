package com.example.sweetPlatinum.menuActivity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.login.LoginActivity
import com.example.sweetPlatinum.login.LoginViewModel
import com.example.sweetPlatinum.saveBattle.SaveBattleActivity
import com.example.sweetPlatinum.setting.SettingActivity
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class MenuActivity : AppCompatActivity(), MenuActivityPresenter.Listener {

   // private val menuActivityPresenter: MenuActivityPresenter by inject { parametersOf(this) }
    private val viewModel: MenuActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

//        val toolbar: Toolbar = findViewById(R.id.menu_actionbar)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.itemIconTintList = null

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
                val loginIntent = Intent(this, LoginActivity::class.java)
                MySharedPreferences(this).deleteData()
                startActivity(loginIntent)
                overridePendingTransition(R.anim.from_left, R.anim.to_right)
    //            menuActivityPresenter.deleteAllHistory()
                onLogoutSuccess()
            }

            R.id.save_battle -> {
                val saveIntent = Intent(this, SaveBattleActivity::class.java)
                startActivity(saveIntent)
                overridePendingTransition(R.anim.from_right, R.anim.to_left)
            }

            R.id.setting -> {
                val settingIntent = Intent(this, SettingActivity::class.java)
                startActivity(settingIntent)
                overridePendingTransition(R.anim.from_right, R.anim.to_left)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onLogoutSuccess() {
        Toast.makeText(this, R.string.logout_success, Toast.LENGTH_SHORT).show()
        finish()
    }

}