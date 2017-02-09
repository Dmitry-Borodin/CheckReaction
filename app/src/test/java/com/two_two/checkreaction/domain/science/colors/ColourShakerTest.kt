package com.two_two.checkreaction.domain.science.colors

import com.two_two.checkreaction.utils.Constants
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * @author Dmitry Borodin on 2017-02-09.
 */

class ColourShakerTest {

    var shaker = ColourShaker()

    @Before
    fun setUp() {
        shaker = ColourShaker()
    }

    @Test
    fun testShakeSize() {
        val ZERO_INDEX = 1
        for (i in 0..100) {
            shaker.shake()
            assertEquals(Constants.COLOURS_AVAILABLE_SCIENCE + ZERO_INDEX, shaker.shakedOrder.size)
        }
    }

    @Test
    fun testShakeNotRepeated() {
        shaker.shake()
        for (i in 0..100) {
            val oldShaked = ArrayList(shaker.shakedOrder)
            shaker.shake()
            val newShaked = ArrayList(shaker.shakedOrder)
            assertOrderDifferent(newShaked, oldShaked)
        }
    }

    private fun assertOrderDifferent(newShaked: ArrayList<Int>, oldShaked: ArrayList<Int>) {
        val LAST_ELEMENT = 1
        for (j in 0..(newShaked.size - LAST_ELEMENT)) {
            assertNotEquals(oldShaked.get(j), newShaked.get(j))
        }
    }

    @Test
    fun testsRestorebleColors() {
        shaker.shake()
        for (i in 0..shaker.shakedOrder.lastIndex) {
            val realIndex = shaker.getRealIndex(shakedIndex = i)
            assertEquals(realIndex, shaker.shakedOrder.indexOf(i))
        }
    }
}