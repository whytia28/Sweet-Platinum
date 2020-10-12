package com.example.sweetPlatinum.battleActivity

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.sweetPlatinum.R
import org.junit.Rule
import org.junit.Test

class MultiPlayerActivityTest {
    @get: Rule
    val activityRule = ActivityTestRule(MultiPlayerActivity::class.java)

    @Test
    fun test_player_one_win() {
        Espresso.onView(ViewMatchers.withId(R.id.rock1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.scissor2)).perform(ViewActions.click())
        Thread.sleep(2000)
    }

    @Test
    fun test_player_two_win() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.scissor2)).perform(ViewActions.click())
        Thread.sleep(2000)
    }

    @Test
    fun test_draw() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.paper2)).perform(ViewActions.click())
        Thread.sleep(2000)
    }

    @Test
    fun test_button_restart() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.scissor2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.iv_restart)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_button_save_battle() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.scissor2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.iv_save)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_button_share_to() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.scissor2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_share)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_score_battle() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rock2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.iv_restart)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rock1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.scissor2)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Thread.sleep(5000)
    }
}