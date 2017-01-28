package com.two_two.checkreaction.domain.game

import android.os.Handler
import java.util.*

/**
 * @author Dmitry Borodin on 2017-01-29.
 */

class FixedDelayTimer() : DelayTimer {
    private val DELAY = 1000L //1 second
    private val random = Random()

    override fun runDelayed(delayTimerCallback: DelayTimerCallback) {
        Handler().postDelayed({ delayTimerCallback.delayedCode() }, DELAY)
    }
}
