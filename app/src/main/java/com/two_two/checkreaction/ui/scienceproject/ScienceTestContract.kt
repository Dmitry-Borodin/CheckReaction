package com.two_two.checkreaction.ui.scienceproject

import com.two_two.checkreaction.models.science.ScienceTestResult

/**
 * @author Dmitry Borodin on 2017-01-29.
 */

interface ScienceTestContract {
    interface View {
        fun setFirstViewColour(color: Int)
        fun setSecondViewColour(color: Int)
        fun setThirdViewColour(color: Int)
        fun setForthViewColour(color: Int)
        fun setTargetColour(color: Int, colorName: String)
        fun navigateToResults(result: ScienceTestResult)
    }

    interface Presenter {
        fun bindActivity(bindedView: View)
        fun unBindActivity()
        fun onFirstViewClicked()
        fun onSecondViewClicked()
        fun onThirdViewClicked()
        fun onForthViewClicked()
    }
}
