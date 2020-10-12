package com.example.sweetPlatinum.menuActivity.ui.battle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.battleActivity.MultiPlayerActivity
import com.example.sweetPlatinum.battleActivity.SinglePlayerActivity
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.pojo.AuthResponse
import com.example.sweetPlatinum.pojo.LoginResponse
import kotlinx.android.synthetic.main.fragment_battle.*


class BattleFragment : Fragment() {

    private lateinit var battleViewModel: BattleViewModel
    private  var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        battleViewModel =
            ViewModelProvider(this).get(BattleViewModel::class.java)
        return inflater.inflate(R.layout.fragment_battle, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = view.context as MenuActivity
        context.supportActionBar?.title = getString(R.string.title_battle)

        if (context.intent.hasExtra("data")) {
            context.intent.getParcelableExtra<LoginResponse.Data>("data")?.let {
                username = it.username
            }
        }
        if (context.intent.hasExtra("dataFromAuth")) {
            context.intent.getParcelableExtra<AuthResponse.Data>("dataFromAuth")?.let {
                username = it.username!!
            }
        }

        tv_multi_player.text = getString(R.string.vs_player, username)
        tv_single_player.text = getString(R.string.vs_cpu, username)

        single_player.setOnClickListener {
            goToSinglePlayer(username)
        }

        multi_player.setOnClickListener {
            goToMultiPlayer(username)
        }

        btn_exit.setOnClickListener {
            activity?.finish()
        }


    }

     private fun goToMultiPlayer(username: String) {
        val multiPlayerIntent = Intent(context, MultiPlayerActivity::class.java)
        multiPlayerIntent.putExtra("username", username)
        startActivity(multiPlayerIntent)
        activity?.overridePendingTransition(R.anim.from_right, R.anim.to_left)
    }

     private fun goToSinglePlayer(username: String) {
        val singlePlayerIntent = Intent(context, SinglePlayerActivity::class.java)
        singlePlayerIntent.putExtra("username", username)
        startActivity(singlePlayerIntent)
        activity?.overridePendingTransition(R.anim.from_right, R.anim.to_left)
    }
}