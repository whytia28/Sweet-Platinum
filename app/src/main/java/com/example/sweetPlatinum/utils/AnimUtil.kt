package com.example.sweetPlatinum.utils

import android.view.View
import android.view.animation.AnimationUtils
import com.example.sweetPlatinum.R

class AnimUtil {

    fun bounceAnimation(view: View) {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.bounce_animation)
        view.startAnimation(animation)
    }

    fun rotateAnimation(view: View) {
        val animation = AnimationUtils.loadAnimation(view.context, R.anim.rotate_animation)
        view.startAnimation(animation)
    }

}