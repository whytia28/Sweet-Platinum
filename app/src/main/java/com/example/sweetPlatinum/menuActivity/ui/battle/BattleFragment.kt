package com.example.sweetPlatinum.menuActivity.ui.battle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sweetPlatinum.R


class BattleFragment : Fragment() {

    private lateinit var battleViewModel: BattleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        battleViewModel =
            ViewModelProvider(this).get(BattleViewModel::class.java)
        return inflater.inflate(R.layout.fragment_battle, container, false)
    }
}