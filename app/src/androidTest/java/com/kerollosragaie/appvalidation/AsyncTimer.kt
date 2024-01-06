package com.kerollosragaie.appvalidation

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import java.util.Timer
import kotlin.concurrent.schedule

object AsyncTimer {
    private var expired = false
    private fun start(delay: Long = 1000){
        expired = false
        Timer().schedule(delay) {
            expired = true
        }
    }

    fun asyncTimer (testRule: ComposeContentTestRule, delay: Long = 1000) {
        start (delay)
        testRule.waitUntil (
            condition = {AsyncTimer.expired},
            timeoutMillis = delay + 1000
        )
    }
}