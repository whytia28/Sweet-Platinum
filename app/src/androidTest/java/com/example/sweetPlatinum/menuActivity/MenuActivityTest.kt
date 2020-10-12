package com.example.sweetPlatinum.menuActivity

import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.sweetPlatinum.R
import org.junit.Rule
import org.junit.Test

class MenuActivityTest {
    @get: Rule
    val activity = ActivityTestRule(MenuActivity::class.java)

    @Test
    fun test_menu_logout() {
        Espresso.openActionBarOverflowOrOptionsMenu(getTargetContext())
        Espresso.onView(ViewMatchers.withId(R.id.logout)).perform(ViewActions.click())
        Thread.sleep(2000)
    }

    @Test
    fun test_menu_save_battle() {
        Espresso.openActionBarOverflowOrOptionsMenu(getTargetContext())
        Espresso.onView(ViewMatchers.withId(R.id.save_battle)).perform(ViewActions.click())
        Thread.sleep(2000)
    }

    @Test
    fun test_multi_player() {
        Espresso.onView(ViewMatchers.withId(R.id.multi_player)).perform(ViewActions.click())
        Thread.sleep(3000)
    }

    @Test
    fun test_single_player() {
        Espresso.onView(ViewMatchers.withId(R.id.single_player)).perform(ViewActions.click())
        Thread.sleep(3000)
    }

    @Test
    fun test_history_menu() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_history)).perform(ViewActions.click())
        Thread.sleep(3000)
    }

    @Test
    fun test_profile_menu() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_profile)).perform(ViewActions.click())
        Thread.sleep(3000)
    }

    @Test
    fun test_edit_profile() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_profile)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_edit)).perform(ViewActions.click())
        Thread.sleep(3000)
    }

    @Test
    fun test_cancel_edit_profile() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_profile)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_edit)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_cancel)).perform(ViewActions.click())
        Thread.sleep(3000)
    }
}