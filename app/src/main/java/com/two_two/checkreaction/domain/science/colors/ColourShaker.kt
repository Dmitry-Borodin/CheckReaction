package com.two_two.checkreaction.domain.science.colors

import com.two_two.checkreaction.utils.Constants
import java.util.*

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ColourShaker() {
    val shakedOrder = ArrayList<Int>()
    private val setOfIndexes = ArrayList<Int>()
    private val random = Random()

    fun shake() {
        val prevShaked = ArrayList(shakedOrder)
        do {
            shakedOrder.clear()
            generateIndexes()
            fillShakedOrder()
        } while (shareElementsWithPrevious(prevShaked))
    }

    private fun shareElementsWithPrevious(prevShaked: ArrayList<Int>): Boolean {
        if (prevShaked.isEmpty()) return false
        if (prevShaked.size != shakedOrder.size) return false
        for (i in 0..shakedOrder.lastIndex) {
            if (shakedOrder.get(i) == prevShaked.get(i)) {
                //match - we should regenerate list
                return true
            }
        }
        return false
    }

    private fun fillShakedOrder() {
        while (setOfIndexes.size > 0) {
            val index = random.nextInt(setOfIndexes.size)
            val element = setOfIndexes.get(index)
            shakedOrder.add(element)
            setOfIndexes.removeAt(index)
        }
    }

    private fun generateIndexes() {
        setOfIndexes.clear()
        for (i in 0..Constants.COLOURS_AVAILABLE_SCIENCE) {
            setOfIndexes.add(i)
        }
    }

    fun getShakedIndex(realIndex: Int): Int {
        if (shakedOrder.size < realIndex) {
            return -1
        }
        return shakedOrder.get(realIndex)
    }

    fun getRealIndex(shakedIndex: Int): Int {
        return shakedOrder.indexOf(shakedIndex)
    }
}