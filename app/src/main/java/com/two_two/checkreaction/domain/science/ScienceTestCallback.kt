package com.two_two.checkreaction.domain.science

import com.two_two.checkreaction.models.science.ScienceAttamptData
import com.two_two.checkreaction.models.science.ScienceTestResult

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
interface ScienceTestCallback {
    fun showNextScreen(attemptData: ScienceAttamptData)
    fun testFinished(result: ScienceTestResult)
}