package com.two_two.checkreaction.domain.science

import com.two_two.checkreaction.domain.science.colors.ColourProvider
import com.two_two.checkreaction.utils.Constants
import java.util.*

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
object ScienceTargetGenerator {
    val excludedElement = 1
    var chosenColorIndex = pickRandomColourIndex()

    fun regenerate() {
        chosenColorIndex = pickRandomColourIndex()
    }

    private fun pickRandomColourIndex() = Random().nextInt(Constants.COLOURS_AVAILABLE_SCIENCE + excludedElement)
}