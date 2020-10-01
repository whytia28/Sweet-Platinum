package com.example.sweetPlatinum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.room.History
import com.example.sweetPlatinum.saveBattle.SaveBattlePresenter
import kotlinx.android.synthetic.main.history_local_item.view.*

class AdapterHistoryLocal(
    private val historyBattle: List<History>, private val presenter: SaveBattlePresenter
) : RecyclerView.Adapter<AdapterHistoryLocal.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.history_local_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(historyBattle[position])
        holder.itemView.tv_delete.setOnClickListener {
            presenter.deleteHistory(historyBattle[position])
            presenter.getListHistory()
        }
    }

    override fun getItemCount(): Int = historyBattle.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(battle: History) {
            with(itemView) {
                tv_date.text = battle.date
                tv_mode.text = battle.mode
                tv_history.text = battle.history
            }
        }
    }
}