package com.example.sweetPlatinum.menuActivity.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.adapter.AdapterHistory
import com.example.sweetPlatinum.menuActivity.MenuActivity
import com.example.sweetPlatinum.pojo.GetBattleResponse
import com.example.sweetPlatinum.sharedPreference.MySharedPreferences
import com.gkemon.XMLtoPDF.PdfGenerator
import com.gkemon.XMLtoPDF.PdfGeneratorListener
import com.gkemon.XMLtoPDF.model.FailureResponse
import com.gkemon.XMLtoPDF.model.SuccessResponse
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HistoryFragment : Fragment() {

    private lateinit var token: String
    private val historyViewModel: HistoryViewModel by viewModel()

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


        btn_download.setOnClickListener {
            PdfGenerator.getBuilder()
                .setContext(activity)
                .fromViewIDSource()
                .fromViewID(activity, R.id.rv_history_battle)
                .setDefaultPageSize(PdfGenerator.PageSize.A4)
                .setFileName("history-sweet-platinum-${Date().time}")
                .setFolderName("Sweet Platinum")
                .openPDFafterGeneration(true)
                .build(object : PdfGeneratorListener() {
                    override fun onFailure(failureResponse: FailureResponse?) {
                        super.onFailure(failureResponse)
                        Toast.makeText(activity, "Download PDF Failed", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSuccess(response: SuccessResponse?) {
                        super.onSuccess(response)
                        Toast.makeText(activity, "Download PDF success", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }

    override fun onResume() {
        super.onResume()
        getHistory()
    }

    private fun getHistory() {
        rv_history_battle.startLoading()
        historyViewModel.getHistory(token).observe(viewLifecycleOwner, {
            getHistorySuccess(it)
            rv_history_battle.stopLoading()
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

    private fun setupUi(listHistory: List<GetBattleResponse.Data>) {
        if (listHistory.isEmpty()) {
            tv_history_empty.visibility = View.VISIBLE
        }
    }

    private fun getHistorySuccess(listHistory: List<GetBattleResponse.Data>) {
        setRecyclerView(listHistory)
    }
}