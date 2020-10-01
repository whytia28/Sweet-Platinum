package com.example.sweetPlatinum.saveBattle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.adapter.AdapterHistoryLocal
import com.example.sweetPlatinum.room.History
import kotlinx.android.synthetic.main.activity_save_battle.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SaveBattleActivity : AppCompatActivity(), SaveBattlePresenter.Listener {
    private val presenter: SaveBattlePresenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_battle)

        supportActionBar?.title = getString(R.string.save_battle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.listener = this
        presenter.getListHistory()
    }

    override fun onResume() {
        super.onResume()
        presenter.getListHistory()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
        return true
    }

    private fun setRecyclerView(listHistory: List<History>) {
        rv_history.layoutManager = LinearLayoutManager(this)
        rv_history.setHasFixedSize(true)
        val adapter = AdapterHistoryLocal(listHistory, presenter)
        rv_history.adapter = adapter
        setupUi(listHistory)
    }

    override fun setupUi(listHistory: List<History>) {
        if (listHistory.isNotEmpty()) {
            tv_history_empty.visibility = View.GONE
        } else {
            tv_history_empty.visibility = View.VISIBLE
        }
    }

    override fun showAllHistory(listHistory: List<History>) {
        runOnUiThread {
            setRecyclerView(listHistory)
        }
    }

    override fun onSuccessDelete() {
        runOnUiThread {
            Toast.makeText(this, getString(R.string.delete_history_success), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onFailedDelete() {
        runOnUiThread {
            Toast.makeText(this, getString(R.string.delete_history_failed), Toast.LENGTH_SHORT)
                .show()
        }
    }
}