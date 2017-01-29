package com.two_two.checkreaction.domain.science.colors

import java.util.*

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ColourShaker() {
    val shakedOrder = ArrayList<Int>()
    val setOfIndexes = ArrayList<Int>()
    val random = Random()

    fun shake() {
        shakedOrder.clear()
        generateIndexes()
        while (setOfIndexes.size > 0) {
            val index = random.nextInt(setOfIndexes.size)
            shakedOrder.add(setOfIndexes.get(index))
            setOfIndexes.removeAt(index)
        }
    }

    private fun generateIndexes() {
        setOfIndexes.clear()
        for (i in 0 .. ColourProvider.COLOURS_AVAILABLE_SCIENCE) {
            setOfIndexes.add(i)
        }
    }

    fun getShakedIndex(realIndex : Int) : Int {
        if (shakedOrder.size < realIndex) {
            return -1
        }
        return shakedOrder.get(realIndex)
    }

    fun getRealIndex(shakedIndex : Int) : Int {
        return shakedOrder.indexOf(shakedIndex)
    }
}