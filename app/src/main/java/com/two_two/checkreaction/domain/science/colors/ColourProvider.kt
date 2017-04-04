package com.two_two.checkreaction.domain.science.colors

import android.content.Context
import android.os.Build
import com.two_two.checkreaction.BuildConfig
import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.App

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ColourProvider(val context: Context = App.getInstance()) {

    //todo add anotation that returning resource
    //todo add annotations to limit available indexes
    fun getColorResource(index: Int): Int {
        if (Build.VERSION.SDK_INT > 23) {
            return context.resources.getColor(getColorId(index), context.theme)
        }else {
            @Suppress("DEPRECATION")
            return context.resources.getColor(getColorId(index))
        }
    }

    fun getColorId(index: Int): Int {
        return when (index) {
            0 -> R.color.white
            1 -> R.color.yellow
            2 -> R.color.red
            3 -> R.color.blue
            else -> R.color.black_overlay
        }
    }

    fun getColourName(index: Int): String {
        return when (index) {
            0 -> context.getString(R.string.colour_white)
            1 -> context.getString(R.string.colour_yellow)
            2 -> context.getString(R.string.colour_red)
            3 -> context.getString(R.string.colour_blue)
            else -> context.getString(R.string.colour_black)
        }
    }
}