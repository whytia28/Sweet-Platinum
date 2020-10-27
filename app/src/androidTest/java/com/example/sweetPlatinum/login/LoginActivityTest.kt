package com.example.sweetPlatinum.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.sweetPlatinum.R
import org.junit.Rule
import org.junit.Test

class LoginActivityTest {
    @get: Rule
    val activityRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun test_login_username_password_correct() {
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("dito@gmail.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("dito123")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        Thread.sleep(15000)
    }

    @Test
    fun test_login_wrong_password() {
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("dito@gmail.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("dito234")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_login_wrong_email() {
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("ditosaya@gmail.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("dito123")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_login_blank_email_password() {
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText(""))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_login)).perform(ViewActions.click())
        Thread.sleep(5000)
    }

    @Test
    fun test_button_reset() {
        Espresso.onView(ViewMatchers.withId(R.id.et_email)).perform(ViewActions.typeText("email@email.com"))
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).perform(ViewActions.typeText("password")).perform(ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_reset)).perform(ViewActions.click())
        Thread.sleep(5000)
    }
}