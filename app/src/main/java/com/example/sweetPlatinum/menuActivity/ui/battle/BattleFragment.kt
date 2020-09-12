package com.example.sweetPlatinum.menuActivity.ui.battle

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweetPlatinum.battleActivity.MultiPlayerActivity
import com.example.sweetplatinum.R
import kotlinx.android.synthetic.main.fragment_battle.*


class BattleFragment : Fragment(), BattlePresenter.Listener {

    private lateinit var battleViewModel: BattleViewModel
    private lateinit var username: String
    private lateinit var presenter: BattlePresenter

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

//        val context = view.context as MenuActivity
//        context.supportActionBar?.title = getString(R.string.title_battle)

//        if (context.intent.hasExtra("data")) {
//            context.intent.getParcelableExtra<LoginResponse.Data>("data")?.let {
//                username = it.username
//            }
//        }
//        if (context.intent.hasExtra("dataFromPrepare")) {
//            context.intent.getParcelableExtra<AuthResponse.Data>("dataFromPrepare")?.let {
//                username = it.username
//            }
//        }

        username = "Pemain"

        tv_multi_player.text = getString(R.string.vs_player, username)
        tv_single_player.text = getString(R.string.vs_cpu, username)

        presenter = BattlePresenter()
        presenter.listener = this

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

    override fun goToMultiPlayer(username: String) {
        val multiPlayerIntent = Intent(context, MultiPlayerActivity::class.java)
        multiPlayerIntent.putExtra("username", username)
        startActivity(multiPlayerIntent)
    }

    override fun goToSinglePlayer(username: String) {
//        val singlePlayerIntent = Intent(context, SinglePlayerActivity::class.java)
//        singlePlayerIntent.putExtra("username", username)
//        startActivity(singlePlayerIntent)
    }
}