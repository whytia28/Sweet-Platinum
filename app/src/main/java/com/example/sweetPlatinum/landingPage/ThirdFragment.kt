package com.example.sweetPlatinum.landingPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sweetPlatinum.R
import com.example.sweetPlatinum.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_third.*


class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivNext.setOnClickListener {
            val intentKeLoginActivity = Intent(activity, LoginActivity::class.java)
            startActivity(intentKeLoginActivity)
            activity?.overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }
    }

}