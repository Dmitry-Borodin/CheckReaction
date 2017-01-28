package com.two_two.checkreaction.models.science

import com.two_two.checkreaction.models.game.TestType

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ScienceTestResult(
        val currectHits: Int, //of 10 attapts
        val average: Long,
        val testType: TestType
) {
    fun getFIrebaseScienceResult() : FirebaseScienceResult {
        return FirebaseScienceResult(currectHits, average)
    }
}