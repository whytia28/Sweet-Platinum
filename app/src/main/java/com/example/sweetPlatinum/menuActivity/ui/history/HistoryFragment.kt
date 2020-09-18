package com.example.sweetPlatinum.menuActivity.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.adapter.AdapterHistory
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.pojo.GetBattleResponse
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HistoryFragment : Fragment(), HistoryPresenter.Listener {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var token: String
    private val presenter: HistoryPresenter by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = view.context as MenuActivity
        context.supportActionBar?.title = getString(R.string.title_history)
        token = MySharedPreferences(context).getData("token").toString()
        presenter.listener = this
        presenter.getHistory(token)
    }

    override fun onResume() {
        super.onResume()
        presenter.getHistory(token)
    }

    private fun setRecyclerView(listHistory: List<GetBattleResponse.Data>) {
        rv_history_battle.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_history_battle.setHasFixedSize(true)
        val adapter = AdapterHistory(listHistory)
        rv_history_battle.adapter = adapter
        setupUi(listHistory)

    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun setupUi(listHistory: List<GetBattleResponse.Data>) {
        if (listHistory.isNotEmpty()) {
            tv_history_empty.visibility = View.GONE
        }
    }

    override fun getHistorySuccess(listHistory: List<GetBattleResponse.Data>) {
        setRecyclerView(listHistory)
    }

    override fun getHistoryFailed(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }
}