package com.two_two.checkreaction.domain.game

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
interface DelayTimer {
    fun runDelayed(delayTimerCallback: DelayTimerCallback)

    fun forgetDelayetCode()
}