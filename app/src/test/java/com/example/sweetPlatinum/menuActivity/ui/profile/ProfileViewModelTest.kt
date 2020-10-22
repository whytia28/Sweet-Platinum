package com.example.sweetPlatinum.menuActivity.ui.profile

import android.graphics.Bitmap
import androidx.lifecycle.Observer
import com.example.sweetPlatinum.di.appModule
import com.example.sweetPlatinum.di.viewModule
import com.example.sweetPlatinum.pojo.GetProfileResponse
import com.example.sweetPlatinum.pojo.UpdateResponse
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

class ProfileViewModelTest: KoinTest {
    private val viewModel: ProfileViewModel by inject()
    private val observerProfile = mock<Observer<GetProfileResponse>>()
    private val observerUpdate = mock<Observer<UpdateResponse>>()
    lateinit var bitmap: Bitmap

    @Before
    fun before() {
        startKoin(listOf(appModule, viewModule))
        TrampolineSchedulerRX.start()
        InstantRuleExecution.start()
    }

    @Test
    fun `Test Get Profile`() {
        viewModel.getProfileUser("token")
        viewModel.dataProfile.observeForever {
            verify(observerProfile, atLeastOnce()).onChanged(it)
        }
    }

//    @Test
//    fun `Test Update Profile`() {
//        viewModel.updateUser("token", bitmap, "ditosa", "dito@gmail.com" )
//        viewModel.dataProfileUpdate.observeForever {
//            verify(observerUpdate, atLeastOnce()).onChanged(it)
//        }
//    }

    @Test
    fun `Test Update Profile`() {
        viewModel.dataProfileUpdate.observeForever {
            verify(observerUpdate, atLeastOnce()).onChanged(it)
        }
    }

    @After
    fun after() {
        stopKoin()
        TrampolineSchedulerRX.tearDown()
        InstantRuleExecution.tearDown()
    }
}
