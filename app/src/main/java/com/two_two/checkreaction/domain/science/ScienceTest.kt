package com.two_two.checkreaction.domain.science

import com.two_two.checkreaction.domain.game.DelayTimerCallback
import com.two_two.checkreaction.domain.game.FixedDelayTimer
import com.two_two.checkreaction.domain.game.StopWatch
import com.two_two.checkreaction.domain.science.colors.ColourProvider
import com.two_two.checkreaction.domain.science.colors.ColourShaker
import com.two_two.checkreaction.models.game.TestType
import com.two_two.checkreaction.models.science.ScienceIterationData
import com.two_two.checkreaction.models.science.ScienceTestResult
import com.two_two.checkreaction.domain.science.ScienceTargetGenerator
import java.util.*

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ScienceTest(val colourProvider: ColourProvider,
                  val colourShaker: ColourShaker = ColourShaker()) {

    val delayTimer = FixedDelayTimer()
    val stopWatch = StopWatch()

    var iteration = 0
    var failedIterations = 0
    var results = ArrayList<Long>()
    var callback: ScienceTestCallback? = null
    var started = false


    fun prepare() {
        iteration = 0
        failedIterations = 0
        results = ArrayList<Long>()
    }

    fun start() {
        if (started) {
            return
        }
        started = true
        prepare()
        beginIteration()
    }

    private fun beginIteration() {
        colourShaker.shake()
        delayTimer.runDelayed(DelayTimerCallback { onTimeOut() })
        val iteractionData = ScienceIterationData(colourShaker.shakedOrder)
        callback?.showNextScreen(iteractionData)
    }

    private fun onTimeOut() {
        onIterationFailed()
    }

    fun onTapped(tailClickedIndex: Int) {
        if (isClickedCurrectly(tailClickedIndex)) {
            onIterationSuccess()
        } else {
            onIterationFailed()
        }
    }

    private fun isClickedCurrectly(tailClickedIndex: Int) =
            ScienceTargetGenerator.chosenColorIndex == (colourShaker.getRealIndex(tailClickedIndex))

    private fun onIterationFailed() {
        failedIterations++
        endIteration()
    }

    private fun onIterationSuccess() {
        results.add(stopWatch.getCurrentResult())
        endIteration()
    }

    private fun endIteration() {
        delayTimer.forgetDelayetCode()
        iteration++
        if (iteration < TestType.SCIENCE_TEST.maxAttempts) {
            beginIteration()
        } else {
            val currentHits = (TestType.SCIENCE_TEST.maxAttempts - failedIterations)
            val result = ScienceTestResult(currectHits = currentHits,
                    average = ResultsGenerator.calcAverageReaction(results),
                    testType = TestType.SCIENCE_TEST)
            callback?.testFinished(result)
            started = false
        }
    }
}