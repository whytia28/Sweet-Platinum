package com.example.sweetPlatinum.battleActivity

import android.content.Context
import androidx.lifecycle.Observer
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.viewModule
import com.example.sweetPlatinum.pojo.PostBattleBody
import com.example.sweetPlatinum.pojo.PostBattleResponse
import com.example.sweetPlatinum.utils.InstantRuleExecution
import com.example.sweetPlatinum.utils.TrampolineSchedulerRX
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest

class GamePlayViewModelTest : KoinTest {
    private val viewModel: GamePlayViewModel by inject()
    private val observer = mock<Observer<PostBattleResponse>>()

    @Before
    fun before() {
        startKoin(listOf(appModule, viewModule)) with (mock { Context::class.java })
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun `Test Save History`() {
        val body = PostBattleBody("Multiplayer", "Player Win")
        viewModel.saveHistory("token", body).observeForever {
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