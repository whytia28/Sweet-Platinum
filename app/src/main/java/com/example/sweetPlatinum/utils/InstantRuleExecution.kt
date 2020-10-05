package com.example.sweetPlatinum.utils

import android.annotation.SuppressLint
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor

object InstantRuleExecution {
    @SuppressLint("RestrictedApi")
    fun start() {
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread() = true

        })
    }

    @SuppressLint("RestrictedApi")
    fun tearDown() {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}