package com.example.sweetPlatinum.battleActivity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.logic.Controller
import com.example.sweetPlatinum.pojo.PostBattleBody
import com.example.sweetPlatinum.room.History
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import com.example.sweetPlatinum.utils.AnimUtil
import kotlinx.android.synthetic.main.activity_single_player.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SinglePlayerActivity : AppCompatActivity() {

    private var playerOne: String = ""
    private var winner: String = ""
    private var username: String? = ""
    private lateinit var message: String
    private lateinit var mode: String
    private lateinit var date: String
    private lateinit var animUtil: AnimUtil

    private val viewModel: GamePlayViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player)

        supportActionBar?.title = getString(R.string.single_player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        username = intent.getStringExtra("username")

        player_one.text = username
        mode = "Singleplayer"
        date = viewModel.getCurrentDate()
        animUtil = AnimUtil()

        rock1.setOnClickListener {
            playerOne = Controller.gameChoice[0]
            setOverlay()
            showResult()
            showButtonShare()
            scoreBattle()
        }

        paper1.setOnClickListener {
            playerOne = Controller.gameChoice[1]
            setOverlay()
            showResult()
            showButtonShare()
            scoreBattle()
        }

        scissor1.setOnClickListener {
            playerOne = Controller.gameChoice[2]
            setOverlay()
            showResult()
            showButtonShare()
            scoreBattle()
        }

        iv_restart.setOnClickListener {
            animUtil.rotateAnimation(it)
            startNew()
        }

        iv_save.setOnClickListener {
            animUtil.bounceAnimation(it)
            val token = MySharedPreferences(applicationContext).getData("token").toString()
            val body = PostBattleBody(mode, winner)
            viewModel.saveHistory(token, body).observe(this, {
                onSuccessSaveHistory()
            })
        }

        btn_share.setOnClickListener {
            shareTo()
        }

        viewModel.scoreBattle.observe(this, {
            battle_score_player.text = it.toString()
        })

        viewModel.scoreBattleOpponent.observe(this, {
            battle_score_cpu.text = it.toString()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
        return true
    }

    private fun scoreBattle() {
        if (winner == "Player Win") {
            viewModel.scoreUp()
        } else if (winner == "Opponent Win") {
            viewModel.scoreUpOpponent()
        }
    }

    private fun showResult() {
        if (playerOne != "") {
            val control = Controller()
            val result = control.singlePlayer(playerOne)
            winner = when (result) {
                "Player Win" -> {
                    "Player Win"
                }
                "Opponent Win" -> {
                    "Opponent Win"
                }
                else -> {
                    "Draw"
                }
            }
            setCpuOverlay()

            message = when (winner) {
                "Player Win" -> {
                    getString(R.string.player_win, username)
                }
                "Opponent Win" -> {
                    getString(R.string.cpu_win)
                }
                else -> {
                    getString(R.string.draw)
                }
            }

            val builder = AlertDialog.Builder(this)
            val dialog = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
            builder.setView(dialog)
            builder.setCustomTitle(tv_result)
            dialog.congrats.text = message
            val dialogMessage = builder.create()
            val handler = Handler()
            handler.postDelayed({
                kotlin.run {
                    dialogMessage.show()
                }
            }, 100)
            dialog.btn_exit.setOnClickListener {
                dialogMessage.dismiss()
            }
            val history = History(null, date, message, mode)
            viewModel.saveHistoryLocal(history)
        }
    }

    private fun setCpuOverlay() {
        when (Controller.cpuChoice) {
            Controller.gameChoice[0] -> {
                rock2.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }

            Controller.gameChoice[1] -> {
                paper2.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }

            Controller.gameChoice[2] -> {
                scissor2.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
        }
    }

    private fun onSuccessSaveHistory() {
        Toast.makeText(
            this,
            getString(R.string.save_history_success),
            Toast.LENGTH_SHORT
        ).show()
        iv_save.setImageResource(R.drawable.ic_saved)
    }

    private fun shareTo() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        val body = getString(R.string.body_share, message)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_game))
        shareIntent.putExtra(Intent.EXTRA_TEXT, body)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
    }

    private fun showButtonShare() {
        if (playerOne.isNotEmpty()) {
            btn_share.visibility = View.VISIBLE
        } else {
            btn_share.visibility = View.GONE
        }
    }


    private fun startNew() {
        playerOne = ""
        winner = ""
        iv_save.setImageResource(R.drawable.ic_save)
        rock1.foreground = null
        rock2.foreground = null
        paper1.foreground = null
        paper2.foreground = null
        scissor1.foreground = null
        scissor2.foreground = null
        btn_share.visibility = View.GONE
    }

    private fun setOverlay() {
        when (playerOne) {
            Controller.gameChoice[0] -> {
                rock1.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }

            Controller.gameChoice[1] -> {
                paper1.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }

            Controller.gameChoice[2] -> {
                scissor1.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
        }
    }
}
