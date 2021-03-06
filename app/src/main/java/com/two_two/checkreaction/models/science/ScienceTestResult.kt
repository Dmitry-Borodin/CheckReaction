package com.two_two.checkreaction.models.science

import com.two_two.checkreaction.models.game.TestType
import java.io.Serializable

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ScienceTestResult(
        val currectHits: Int, //of 10 attapts
        val average: Long,
        val testType: TestType
) : Serializable {
    companion object {
        val TAG = "ScienceTestResult"
    }
}