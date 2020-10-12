package com.example.sweetPlatinum.landingPage

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.sweetPlatinum.R
import org.junit.Rule
import org.junit.Test

class LandingActivityTest {
    @get: Rule
    val activityRule = ActivityTestRule(LandingActivity::class.java)

    @Test
    fun test_landing_page() {
        Espresso.onView(ViewMatchers.withId(R.id.ivNext)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.ivNext)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.ivNext)).perform(ViewActions.click())
        Thread.sleep(3000)
    }
}