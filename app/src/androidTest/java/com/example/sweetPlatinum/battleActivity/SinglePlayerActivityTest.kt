package com.example.sweetPlatinum.battleActivity

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.sweetPlatinum.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class SinglePlayerActivityTest {
    @get: Rule
    val activityRule = ActivityTestRule(SinglePlayerActivity::class.java)

    @Test
    fun test_button_restart() {
        Espresso.onView(ViewMatchers.withId(R.id.rock1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.iv_restart)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_button_save_battle() {
        Espresso.onView(ViewMatchers.withId(R.id.rock1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.iv_save)).perform(ViewActions.click())
        Thread.sleep(3000)
    }

    @Test
    fun test_button_share_to() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_share)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_score_battle() {
        Espresso.onView(ViewMatchers.withId(R.id.paper1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.iv_restart)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rock1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_exit)).inRoot(RootMatchers.isDialog()).perform(ViewActions.click())
        Thread.sleep(5000)
    }
}