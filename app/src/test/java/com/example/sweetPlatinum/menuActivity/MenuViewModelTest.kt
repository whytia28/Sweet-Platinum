package com.example.sweetPlatinum.menuActivity

import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.viewModule
import com.example.sweetPlatinum.utils.InstantRuleExecution
import com.example.sweetPlatinum.utils.TrampolineSchedulerRX
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.inject

class MenuViewModelTest : KoinTest {
    private val viewModel: MenuViewModel by inject { parametersOf(this) }

    @Before
    fun before() {
        startKoin { modules(appModule, viewModule) }
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun `Test Delete Data`() {
        viewModel.deleteAllHistory()
    }

    @After
    fun after(){
        stopKoin()
        TrampolineSchedulerRX.tearDown()
        InstantRuleExecution.tearDown()
    }
}