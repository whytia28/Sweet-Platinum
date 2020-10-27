package com.example.sweetPlatinum.battleActivity


import androidx.lifecycle.Observer
import androidx.test.platform.app.InstrumentationRegistry
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
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.inject

class GamePlayViewModelTest : KoinTest {
    private val viewModel: GamePlayViewModel by inject { parametersOf(this) }
    private val observer = mock<Observer<PostBattleResponse>>()
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun before() {
        startKoin {
            modules(appModule, viewModule)
            androidContext(appContext)
        }
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun test_Save_History() {
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