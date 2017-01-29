package com.two_two.checkreaction.domain.science

import java.util.*

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
object ResultsGenerator {
    fun calcAverageReaction(resultList: ArrayList<Long>): Long {
        if (resultList.size == 0) return 0
        var resultSumm: Long = 0
        for (result in resultList) resultSumm += result
        val avg = resultSumm / resultList.size
        return avg
    }

    fun calcMedianReaction(resultList: ArrayList<Long>): Long {
        if (resultList.size == 0) return 0
        //If there is middle element - it is median, else - average for 2 middle elements
        if (resultList.size % 2 == 1) {
            val median = resultList[resultList.size / 2]
            return median
        } else {
            val m1 = resultList[resultList.size / 2]
            val m2 = resultList[resultList.size / 2 - 1]
            val averageForTwoMedians = (m1 + m2) / 2
            return averageForTwoMedians
        }
    }
}