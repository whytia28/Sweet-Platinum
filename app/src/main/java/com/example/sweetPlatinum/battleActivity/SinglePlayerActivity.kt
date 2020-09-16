package com.example.sweetPlatinum.battleActivity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.example.binarchapter8.pojo.PostBattleBody
import com.example.sweetPlatinum.logic.Controller
import com.example.sweetplatinum.R

class SinglePlayerActivity : AppCompatActivity(), SinglePlayerPresenter.Listener {

   /* private var pilihanSatu: String = ""
    private var pemenang: String = ""
    private var username: String? = ""

    @Inject
    lateinit var presenter: SinglePlayerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_player)


        username = intent.getStringExtra("username")
        presenter.listener = this
        pemain1.text = username

        batu1.setOnClickListener {
            pilihanSatu = Controller.pilihanGame[0]
            presenter.setOverlay()
            presenter.showResult()

        }

        kertas1.setOnClickListener {
            pilihanSatu = Controller.pilihanGame[1]
            presenter.setOverlay()
            presenter.showResult()
        }

        gunting1.setOnClickListener {
            pilihanSatu = Controller.pilihanGame[2]
            presenter.setOverlay()
            presenter.showResult()
        }

        iv_restart.setOnClickListener {
            presenter.startNew()
        }

        iv_save.setOnClickListener {
            val token = MySharedPreferences(applicationContext).getData("token").toString()
            val mode = "Singleplayer"
            val body = PostBattleBody(mode, pemenang)
            presenter.saveHistory(token, body)
        }

        btn_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun showResult() {
        if (pilihanSatu != "") {
            val control = Controller()
            val hasilMain = control.caraMainCpu(pilihanSatu)
            pemenang = when (hasilMain) {
                "Player Win" -> {
                    "Player Win"
                }
                "CPU 2 menang" -> {
                    "Opponent Win"
                }
                else -> {
                    getString(R.string.hasil_draw)
                }
            }
            presenter.setCpuOverlay()

            val builder = AlertDialog.Builder(this)
            val dialog = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
            builder.setView(dialog)
            builder.setCustomTitle(hasil)
            dialog.selamat.text = pemenang
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
        }
    }

    override fun setCpuOverlay() {
        when (Controller.pilihanCpu) {
            Controller.pilihanGame[0] -> {
                batu2.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[1] -> {
                kertas2.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[2] -> {
                gunting2.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
        }
    }

    override fun showSuccessSave() {
        Toast.makeText(
            this@PemainVsCpu,
            getString(R.string.add_history_success),
            Toast.LENGTH_SHORT
        ).show()
        iv_save.setImageResource(R.drawable.ic_save_active)
    }

    override fun showFailedSave(errorMessage: String) {
        Toast.makeText(
            this@PemainVsCpu,
            errorMessage,
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun startNew() {
        pilihanSatu = ""
        iv_save.setImageResource(R.drawable.ic_save)
        batu1.foreground = null
        batu2.foreground = null
        kertas1.foreground = null
        kertas2.foreground = null
        gunting1.foreground = null
        gunting2.foreground = null
    }

    override fun setOverlay() {
        when (pilihanSatu) {
            Controller.pilihanGame[0] -> {
                batu1.foreground = ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[1] -> {
                kertas1.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }

            Controller.pilihanGame[2] -> {
                gunting1.foreground =
                    ResourcesCompat.getDrawable(resources, R.drawable.overlay, null)
            }
        }
    }
*/
}
