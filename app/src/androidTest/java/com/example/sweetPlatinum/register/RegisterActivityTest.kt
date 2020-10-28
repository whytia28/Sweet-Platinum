package com.example.sweetPlatinum.register

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.sweetPlatinum.R
import org.junit.Rule
import org.junit.Test

class RegisterActivityTest {
    @get: Rule
    val activityRule = ActivityTestRule(RegisterActivity::class.java)

    @Test
    fun test_register_with_correct_requirement() {
        Espresso.onView(ViewMatchers.withId(R.id.et_username)).perform(ViewActions.typeText("testing"))
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("testing@email.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("test123")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_register)).perform(ViewActions.click())
        Thread.sleep(10000)
    }

    @Test
    fun test_register_with_exist_email() {
        Espresso.onView(ViewMatchers.withId(R.id.et_username)).perform(ViewActions.typeText("testing"))
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("dito@gmail.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("test123")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_register)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_register_with_wrong_username() {
        Espresso.onView(ViewMatchers.withId(R.id.et_username)).perform(ViewActions.typeText("test"))
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("testing@email.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("test123")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_register)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_button_reset() {
        Espresso.onView(ViewMatchers.withId(R.id.et_username)).perform(ViewActions.typeText("testing"))
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("testing@email.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("test123")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_reset)).perform(ViewActions.click())
        Thread.sleep(3000)
    }
}