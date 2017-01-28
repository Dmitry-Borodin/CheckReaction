package com.two_two.checkreaction.domain.game

import android.os.Handler

import java.util.Random

/**
 * Created by Dmitry Borodin on 1/4/2016.
 * Manually configured delay timer.
 * Runs provided code after random delay with preconfigured settings.
 */
class RandomDelayTimer : DelayTimer {
    private val mRandom = Random()

    override fun runDelayed(delayTimerCallback: DelayTimerCallback) {
        Handler().postDelayed({ delayTimerCallback.delayedCode() }, randomTime.toLong())
    }

    private //two randoms to more nice delay distribution - less possible min delay
    val randomTime: Int
        get() = mRandom.nextInt(RANDOM_DELAY1) + mRandom.nextInt(RANDOM_DELAY2) + MIN_DELAY

    companion object {
        private val MIN_DELAY = 500
        private val RANDOM_DELAY1 = 5000
        private val RANDOM_DELAY2 = 1000
    }

}
