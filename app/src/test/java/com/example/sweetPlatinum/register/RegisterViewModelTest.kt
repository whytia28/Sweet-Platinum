package com.example.sweetPlatinum.register

import androidx.lifecycle.Observer
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.dbModule
import com.example.sweetPlatinum.di.repositoryModule
import com.example.sweetPlatinum.di.viewModule
import com.example.sweetPlatinum.pojo.RegisterResponse
import com.example.sweetPlatinum.util.InstantRuleExecution
import com.example.sweetPlatinum.util.TrampolineSchedulerRX
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import retrofit2.Response


class RegisterViewModelTest: KoinTest{
    private val viewModel: RegisterViewModel by inject()
    private val observer = mock<Observer<Response<RegisterResponse>>>()

    @Before
    fun before(){
        startKoin(listOf(appModule, dbModule, repositoryModule, viewModule))
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun `Test Register`(){
        viewModel.registerPerson("emailku1@mail.com", "username1", "password").observeForever(){
            verify(observer, atLeastOnce()).onChanged(it)
        }
    }

    @After
    fun after(){
        stopKoin()
    }
}