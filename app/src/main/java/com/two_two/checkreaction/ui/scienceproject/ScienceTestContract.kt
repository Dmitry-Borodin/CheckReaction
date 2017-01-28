package com.two_two.checkreaction.ui.scienceproject

import com.two_two.checkreaction.models.science.ScienceTestResult

/**
 * @author Dmitry Borodin on 2017-01-29.
 */

interface ScienceTestContract {
    interface View {
        fun setFirstViewColor(color: Int)
        fun setSecondViewColor(color: Int)
        fun setThirdViewColor(color: Int)
        fun setForthViewColor(color: Int)
        fun setRightColor(color: Int, colorName: String)
        fun navigateToResults(result: ScienceTestResult)
    }

    interface Presenter {
        fun bindActivity(view: View)
        fun unBindActivity()
        fun onFirstViewClicked()
        fun onSecondViewClicked()
        fun onThirdViewClicked()
        fun onForthViewClicked()
    }
}
