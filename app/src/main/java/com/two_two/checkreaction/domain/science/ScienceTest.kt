package com.two_two.checkreaction.domain.science

import com.two_two.checkreaction.domain.game.RandomDelayTimer
import com.two_two.checkreaction.domain.game.StopWatch
import com.two_two.checkreaction.domain.science.colors.ColourProvider
import com.two_two.checkreaction.domain.science.colors.ColourShaker
import java.util.*

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ScienceTest(val colourProvider: ColourProvider,
                  val colourShaker: ColourShaker = ColourShaker()) {

    val delayTimer = RandomDelayTimer()
    val stopWatch = StopWatch()

    var iteration = 0
    var failedIterations = 0
    var results = ArrayList<Int>()
    var currectColor = 0
    var callback: ScienceTestCallback? = null


    fun prepare() {
        val excludedElement = 1
        iteration = 0
        failedIterations = 0
        results = ArrayList<Int>()
        currectColor = Random().nextInt(ColourProvider.COLOURS_AVAILABLE_SCIENCE + excludedElement)
        colourShaker.shake()
    }

    fun start() {

    }

    fun onTapped(tailIndex: Int) {

    }

}