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
import kotlinx.android.synthetic.main.activity_single_player.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SinglePlayerActivity : AppCompatActivity(), GamePlayPresenter.Listener {

    private var playerOne: String = ""
    private var winner: String = ""
    private var username: String? = ""
    private lateinit var message: String
    private lateinit var mode: String
    private lateinit var date: String

    private val presenter: GamePlayPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player)

        supportActionBar?.title = getString(R.string.single_player)
        username = intent.getStringExtra("username")
        presenter.listener = this
        player_one.text = username
        mode = "Singleplayer"
        date = presenter.getCurrentDate()

        rock1.setOnClickListener {
            playerOne = Controller.gameChoice[0]
            setOverlay()
            showResult()
            showButtonShare()
        }

        paper1.setOnClickListener {
            playerOne = Controller.gameChoice[1]
            setOverlay()
            showResult()
            showButtonShare()
        }

        scissor1.setOnClickListener {
            playerOne = Controller.gameChoice[2]
            setOverlay()
            showResult()
            showButtonShare()
        }

        iv_restart.setOnClickListener {
            startNew()
        }

        iv_save.setOnClickListener {
            val token = MySharedPreferences(applicationContext).getData("token").toString()
            val body = PostBattleBody(mode, winner)
            presenter.saveHistory(token, body)
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
        btn_share.setOnClickListener {
            shareTo()
        }
    }

    override fun showResult() {
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
            }, 1000)
            dialog.btn_exit.setOnClickListener {
                dialogMessage.dismiss()
            }
            val history = History(null, date, message, mode)
            presenter.saveHistoryLocal(history)
        }
    }

    override fun setCpuOverlay() {
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

    override fun onSuccessSaveHistory() {
        Toast.makeText(
            this,
            getString(R.string.save_history_success),
            Toast.LENGTH_SHORT
        ).show()
        iv_save.setImageResource(R.drawable.ic_saved)
    }

    override fun onFailedSaveHistory(errorMessage: String) {
        Toast.makeText(
            this,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun shareTo() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        val body = getString(R.string.body_share, message)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_game))
        shareIntent.putExtra(Intent.EXTRA_TEXT, body)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
    }

    override fun showButtonShare() {
        if (playerOne.isNotEmpty() ) {
            btn_share.visibility = View.VISIBLE
        } else {
            btn_share.visibility = View.GONE
        }    }


    override fun startNew() {
        playerOne = ""
        iv_save.setImageResource(R.drawable.ic_save)
        rock1.foreground = null
        rock2.foreground = null
        paper1.foreground = null
        paper2.foreground = null
        scissor1.foreground = null
        scissor2.foreground = null
        btn_share.visibility = View.GONE
    }

    override fun setOverlay() {
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}
