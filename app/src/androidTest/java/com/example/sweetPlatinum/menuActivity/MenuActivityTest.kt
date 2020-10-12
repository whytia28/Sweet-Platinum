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
    fun test_menu_login() {
        Espresso.openActionBarOverflowOrOptionsMenu(getTargetContext())
        Espresso.onView(ViewMatchers.withText(R.id.logout)).perform(ViewActions.click())
        Thread.sleep(2000)
    }
}