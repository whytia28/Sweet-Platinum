package com.example.sweetPlatinum.battleActivity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.sweetPlatinum.logic.Controller
import com.example.sweetPlatinum.pojo.PostBattleBody
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.room.History
import kotlinx.android.synthetic.main.activity_multi_player.*
import kotlinx.android.synthetic.main.custom_alert_dialog.*
import kotlinx.android.synthetic.main.custom_alert_dialog.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MultiPlayerActivity : AppCompatActivity(), GamePlayPresenter.Listener {
    private var playerOne: String = ""
    private var playerTwo: String = ""
    private var winner: String = ""
    private lateinit var message: String
    private var username: String? = ""
    private lateinit var mode: String
    private lateinit var date: String

    private val presenter: GamePlayPresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_player)

        supportActionBar?.title = getString(R.string.multi_player)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        username = intent.getStringExtra("username")
        presenter.listener = this
        player_one.text = username
        mode = "Multiplayer"
        date = presenter.getCurrentDate()

        rock1.setOnClickListener {
            playerOne = Controller.gameChoice[0]
            setOverlay()
            showResult()
            showButtonShare()
            onPlayerSelected()
        }
        paper1.setOnClickListener {
            playerOne = Controller.gameChoice[1]
            setOverlay()
            showResult()
            showButtonShare()
            onPlayerSelected()
        }
        scissor1.setOnClickListener {
            playerOne = Controller.gameChoice[2]
            setOverlay()
            showResult()
            showButtonShare()
            onPlayerSelected()
        }
        rock2.setOnClickListener {
            playerTwo = Controller.gameChoice[0]
            setOverlay()
            showResult()
            showButtonShare()
            onPlayerSelected()
        }
        paper2.setOnClickListener {
            playerTwo = Controller.gameChoice[1]
            setOverlay()
            showResult()
            showButtonShare()
            onPlayerSelected()
        }
        scissor2.setOnClickListener {
            playerTwo = Controller.gameChoice[2]
            setOverlay()
            showResult()
            showButtonShare()
            onPlayerSelected()
        }
        iv_restart.setOnClickListener {
            startNew()
        }
        iv_save.setOnClickListener {
            val token = MySharedPreferences(applicationContext).getData("token").toString()
            val body = PostBattleBody(mode, winner)
            presenter.saveHistory(token, body)
        }

        btn_share.setOnClickListener {
            shareTo()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun onPlayerSelected() {
        if (playerOne.isNotEmpty() && playerTwo.isEmpty()) {
            rock1.visibility = View.GONE
            paper1.visibility = View.GONE
            scissor1.visibility = View.GONE
        } else if (playerOne.isEmpty() && playerTwo.isNotEmpty()) {
            rock2.visibility = View.GONE
            paper2.visibility = View.GONE
            scissor2.visibility = View.GONE
        } else {
            rock1.visibility = View.VISIBLE
            paper1.visibility = View.VISIBLE
            scissor1.visibility = View.VISIBLE
            rock2.visibility = View.VISIBLE
            paper2.visibility = View.VISIBLE
            scissor2.visibility = View.VISIBLE
        }
    }

    override fun startNew() {
        playerOne = ""
        playerTwo = ""
        iv_save.setImageResource(R.drawable.ic_save)
        rock1.foreground = null
        paper1.foreground = null
        scissor1.foreground = null
        rock2.foreground = null
        paper2.foreground = null
        scissor2.foreground = null
        btn_share.visibility = View.GONE
    }

    override fun showResult() {
        if (playerOne != "" && playerTwo != "") {
            val controller = Controller()
            val result = controller.multiPlayer(playerOne, playerTwo)
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

            message = when (winner) {
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
            val history = History(null, date, message, mode)
            presenter.saveHistoryLocal(history)
        }
    }

    override fun setCpuOverlay() {

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
        when (playerTwo) {
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
        Toast.makeText(this, getString(R.string.save_history_success), Toast.LENGTH_SHORT).show()
        iv_save.setImageResource(R.drawable.ic_saved)
    }

    override fun onFailedSaveHistory(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
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
        if (playerOne.isNotEmpty() && playerTwo.isNotEmpty()) {
            btn_share.visibility = View.VISIBLE
        } else {
            btn_share.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}