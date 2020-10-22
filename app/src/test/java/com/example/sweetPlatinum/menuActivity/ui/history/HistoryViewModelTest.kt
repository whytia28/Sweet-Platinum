package com.example.sweetPlatinum.menuActivity.ui.history

import androidx.lifecycle.Observer
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.viewModule
import com.example.sweetPlatinum.pojo.GetBattleResponse
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


class HistoryViewModelTest : KoinTest {
    private val viewModel: HistoryViewModel by inject()
    private val observer = mock<Observer<List<GetBattleResponse.Data>>>()

    @Before
    fun before() {
        startKoin(listOf(appModule, viewModule))
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun `Test History Battle`() {
        viewModel.getHistory("token").observeForever {
            verify(observer, atLeastOnce()).onChanged(it)
        }
    }

    @After
    fun after() {
        stopKoin()
        TrampolineSchedulerRX.tearDown()
        InstantRuleExecution.tearDown()
    }
}