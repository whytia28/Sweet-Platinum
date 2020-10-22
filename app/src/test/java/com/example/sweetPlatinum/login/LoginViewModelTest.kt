package com.example.sweetPlatinum.login

import androidx.lifecycle.Observer
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.viewModule
import com.example.sweetPlatinum.pojo.LoginResponse
import com.example.sweetPlatinum.utils.InstantRuleExecution
import com.example.sweetPlatinum.utils.TrampolineSchedulerRX
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.skyscreamer.jsonassert.JSONAssert

class LoginViewModelTest : KoinTest {
    private val viewModel: LoginViewModel by inject()
    private val observer = mock<Observer<LoginResponse>>()

    @Before
    fun before() {
        startKoin(listOf(appModule, viewModule))
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun `Test Login Success`() {
        viewModel.loginPerson("dito@gmail.com", "pass123", true)
        viewModel.loginData.observeForever {
            verify(observer, atLeastOnce()).onChanged(it)
        }
    }


    @Test
    fun `Test Login Wrong Password`() {
        viewModel.loginPerson("dito@gmail.com", "pass123", true)
        viewModel.errorData.observeForever {
            val expectedJson = "{\"success\":false,\"errors\":\"Wrong password!\"}"
            JSONAssert.assertEquals(expectedJson, it, true)
        }
    }

    @Test
    fun `Test Login Wrong Email`() {
        viewModel.loginPerson("ditowa@gmail.coom", "pass123", true)
        viewModel.errorData.observeForever {
            val expectedJson = "{\"success\":false,\"errors\":\"Email doesn't exist!\"}"
            JSONAssert.assertEquals(expectedJson, it, true)
        }
    }

    @After
    fun after() {
        stopKoin()
        TrampolineSchedulerRX.tearDown()
        InstantRuleExecution.tearDown()
    }
}