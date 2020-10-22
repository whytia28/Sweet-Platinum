package com.example.sweetPlatinum.register

import androidx.lifecycle.Observer
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.viewModule
import com.example.sweetPlatinum.pojo.RegisterResponse
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

class RegisterViewModelTest : KoinTest {
    private val viewModel: RegisterViewModel by inject()
    private val observer = mock<Observer<RegisterResponse>>()


    @Before
    fun before() {
        startKoin(listOf(appModule, viewModule))
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun `Test Register Success`() {
        viewModel.registerPerson("sembarang@email.com", "sembarang", "sembarang")
        viewModel.registerData.observeForever {
            verify(observer, atLeastOnce()).onChanged(it)
        }
    }

    @Test
    fun `Test Short Username`() {
        viewModel.registerPerson("sembarang@email.com", "dodi", "sembarang")
        viewModel.registerError.observeForever {
            val expectedJson = "{\n" +
                    "  \"success\": false,\n" +
                    "  \"errors\": \"User validation failed: username: Path `username` (`dodi`) is shorter than the minimum allowed length (6).\"\n" +
                    "}"
            JSONAssert.assertEquals(expectedJson, it, true)
        }
    }

    @Test
    fun `Test Email Has Registered`() {
        viewModel.registerPerson("dito@gmail.com", "username", "sembarang")
        viewModel.registerError.observeForever {
            val expectedJson = "{\n" +
                    "  \"success\": false,\n" +
                    "  \"errors\": \"E11000 duplicate key error collection: binar-gdd.users index: email_1 dup key: { email: \\\"dito@gmail.com\\\" }\"\n" +
                    "}"
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