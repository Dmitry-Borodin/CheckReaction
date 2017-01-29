package com.two_two.checkreaction.domain.game

import android.os.Handler

/**
 * @author Dmitry Borodin on 2017-01-29.
 */

class FixedDelayTimer : DelayTimer {


    private val DELAY = 1000L //1 second
    private val handler = Handler()
    private var runnable: Runnable? = null

    override fun runDelayed(delayTimerCallback: DelayTimerCallback) {
        runnable = Runnable { delayTimerCallback.delayedCode() }
        handler.postDelayed(runnable, DELAY)
    }

    override fun forgetDelayetCode() {
        handler.removeCallbacks(runnable)
    }

}
