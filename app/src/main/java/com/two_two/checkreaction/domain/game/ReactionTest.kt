package com.two_two.checkreaction.domain.game

import android.util.Log

import com.two_two.checkreaction.models.game.TestResult
import com.two_two.checkreaction.models.game.TestType

import java.util.ArrayList

/**
 * Model for reaction test itself.
 *
 * Call [.startTest] to start test,
 * And [.onTap] when user provides reaction.
 */
class ReactionTest(val testType: TestType) {
    private var mIsRunning = false
    private var mIsReadyForNextTap = false
    private val mRandomDelayTimer = RandomDelayTimer()
    private var mTestCallback: ReactionTestCallback? = null
    private var mStartIterationTime: Long = 0
    private var mIteration: Int = 0
    private val mResultList = ArrayList<Long>()

    fun startTest(testCallback: ReactionTestCallback) {
        if (mIsRunning) {
            throw RuntimeException("startTest called, but already run")
        }
        mIteration = 1
        mTestCallback = testCallback
        mIsRunning = true
        startTestIteration()
    }

    /**
     * Should be called when user actually interact with test.
     */
    fun onTap() {
        if (!mIsRunning) {
            Log.e(TAG, "onTap called, but test did not run")
            return
        }
        if (mIsReadyForNextTap) {
            onIterationSucceed()
            mIsReadyForNextTap = false
        } else {
            proceedFailResults()
        }
    }

    private fun onIterationSucceed() {
        val reaction = System.currentTimeMillis() - mStartIterationTime
        mResultList.add(reaction)
        mIteration++
        if (mIteration <= testType.maxAttempts) {
            startTestIteration()
        } else {
            proceedSuccessResults()
        }
    }

    private fun startTestIteration() {
        mTestCallback?.onWaitForNextTest(mIteration, testType.maxAttempts)
        mRandomDelayTimer.runDelayed(DelayTimerCallback { this.startWaitingForReaction() })
    }

    private fun startWaitingForReaction() {
        mStartIterationTime = System.currentTimeMillis()
        mTestCallback?.onReadyForNextTap()
        mIsReadyForNextTap = true
    }

    private fun proceedFailResults() {
        mIsRunning = false
        val noResult: Long = 0 //test filed, no results
        val isFailed = true
        mTestCallback?.onTestFinished(TestResult(noResult, noResult, isFailed, testType))
    }

    private fun proceedSuccessResults() {
        mIsRunning = false
        val avg = calcAverageReaction()
        val median = calcMedianReaction()
        val isFailed = false
        mTestCallback?.onTestFinished(TestResult(avg, median, isFailed, testType))
    }

    private fun calcAverageReaction(): Long {
        if (mResultList.size == 0) return 0
        var resultSumm: Long = 0
        for (result in mResultList) resultSumm += result
        val avg = resultSumm / mResultList.size
        return avg
    }

    private fun calcMedianReaction(): Long {
        if (mResultList.size == 0) return 0
        //If there is middle element - it is median, else - average for 2 middle elements
        if (mResultList.size % 2 == 1) {
            val median = mResultList[mResultList.size / 2]
            return median
        } else {
            val m1 = mResultList[mResultList.size / 2]
            val m2 = mResultList[mResultList.size / 2 - 1]
            val averageForTwoMedians = (m1 + m2) / 2
            return averageForTwoMedians
        }
    }

    //*******************************************************
    // Section: Helpers
    //*******************************************************

    interface ReactionTestCallback {
        fun onReadyForNextTap()
        fun onTestFinished(result: TestResult)
        fun onWaitForNextTest(currentAttampt: Int, maxAttempts: Int)
    }

    companion object {
        private val TAG = "ReactionTest"
    }

}
