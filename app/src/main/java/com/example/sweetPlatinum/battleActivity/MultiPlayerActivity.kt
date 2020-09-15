package com.example.sweetPlatinum.battleActivity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.sweetPlatinum.logic.Controller
import com.example.sweetPlatinum.pojo.PostBattleBody
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import com.example.sweetplatinum.R
import kotlinx.android.synthetic.main.activity_multi_player.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MultiPlayerActivity : AppCompatActivity(), MultiPlayerPresenter.Listener {
    private var playerOne: String = ""
    private var playerTwo: String = ""
    private var winner: String = ""
    private lateinit var message: String
    private var username: String? = ""

    private val presenter: MultiPlayerPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_player)

        username = intent.getStringExtra("username")
        presenter.listener = this

        rock1.setOnClickListener {
            playerOne = Controller.gameChoice[0]
            setOverlay()
            showResult()
        }
        paper1.setOnClickListener {
            playerOne = Controller.gameChoice[1]
            setOverlay()
            showResult()
        }
        scissor1.setOnClickListener {
            playerOne = Controller.gameChoice[2]
            setOverlay()
            showResult()
        }
        rock2.setOnClickListener {
            playerTwo = Controller.gameChoice[0]
            setOverlay()
            showResult()
        }
        paper2.setOnClickListener {
            playerTwo = Controller.gameChoice[1]
            setOverlay()
            showResult()
        }
        scissor2.setOnClickListener {
            playerTwo = Controller.gameChoice[2]
            setOverlay()
            showResult()
        }
        iv_restart.setOnClickListener {
            startNew()
        }
        iv_save.setOnClickListener {
            val token = MySharedPreferences(applicationContext).getData("key").toString()
            val mode = "Multiplayer"
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

    override fun startNew() {
        playerOne = ""
        playerTwo = ""
        rock1.foreground = null
        paper1.foreground = null
        scissor1.foreground = null
        rock2.foreground = null
        paper2.foreground = null
        scissor2.foreground = null
    }

    override fun showResult() {
        if (playerOne != "" && playerTwo != "") {
            val controller = Controller()
            val result = controller.multiPlayer(playerOne, playerTwo)
            winner = when(result) {
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

            message = when(winner) {
                "Player Win" -> {
                    getString(R.string.player_win, username)
                }
                "Opponent Win" -> {
                    getString(R.string.player_2_win)
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
            dialogMessage.show()
            dialog.btn_exit.setOnClickListener {
                dialogMessage.dismiss()
            }

        }
    }

    override fun setOverlay() {
        when(playerOne) {
            Controller.gameChoice[0] -> {
                rock1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
            Controller.gameChoice[1] -> {
                paper1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
            Controller.gameChoice[2] -> {
                scissor1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
        }
        when(playerTwo) {
            Controller.gameChoice[0] -> {
                rock2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
            Controller.gameChoice[1] -> {
                paper2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
            Controller.gameChoice[2] -> {
                scissor2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.background_selection, null)
            }
        }
    }

    override fun onSuccessSaveHistory() {
        Toast.makeText(this, getString(R.string.save_history_success), Toast.LENGTH_SHORT).show()
        iv_save.setImageResource(R.drawable.ic_saved)
    }

    override fun onFailedSaveHistory(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun shareTo() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        val  body = getString(R.string.body_share, message)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_game))
        shareIntent.putExtra(Intent.EXTRA_TEXT, body)
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)))
    }
}