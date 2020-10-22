package com.example.sweetPlatinum.menuActivity.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.adapter.AdapterHistory
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.pojo.GetBattleResponse
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HistoryFragment : Fragment() {

    private lateinit var token: String
    private val historyViewModel: HistoryViewModel by inject { parametersOf(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = view.context as MenuActivity
        context.supportActionBar?.title = getString(R.string.title_history)
        token = MySharedPreferences(context).getData("token").toString()
        getHistory()
    }

    override fun onResume() {
        super.onResume()
        getHistory()
    }

    private fun getHistory() {
        showProgressBar()
        historyViewModel.getHistory(token).observe(viewLifecycleOwner, {
            getHistorySuccess(it)
            hideProgressBar()
        })
    }

    private fun setRecyclerView(listHistory: List<GetBattleResponse.Data>) {
        rv_history_battle.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_history_battle.setHasFixedSize(true)
        val adapter = AdapterHistory(listHistory)
        rv_history_battle.adapter = adapter
        setupUi(listHistory)

    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    private fun setupUi(listHistory: List<GetBattleResponse.Data>) {
        if (listHistory.isNotEmpty()) {
            tv_history_empty.visibility = View.GONE
        }
    }

    private fun getHistorySuccess(listHistory: List<GetBattleResponse.Data>) {
        setRecyclerView(listHistory)
    }
}