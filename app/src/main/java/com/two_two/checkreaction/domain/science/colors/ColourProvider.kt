package com.two_two.checkreaction.domain.science.colors

import android.content.Context
import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.App

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ColourProvider(val context: Context = App.getInstance()) {

    companion object {
        val COLOURS_AVAILABLE_SCIENCE = 3 //including 0
    }
    //todo add anotation that returning  resource
    //todo add annotations to limit available indexes
    fun getColorResource(index: Int): Int {
        return when (index) {
            0 -> R.color.white
            1 -> R.color.yellow
            2 -> R.color.red
            3 -> R.color.blue
            else -> R.color.black_overlay
        }
    }

    fun getColourName(index: Int) : String {
        return when (index) {
            0 -> context.getString(R.string.colour_white)
            1 -> context.getString(R.string.colour_yellow)
            2 -> context.getString(R.string.colour_red)
            3 -> context.getString(R.string.colour_blue)
            else -> context.getString(R.string.colour_black)
        }
    }
}