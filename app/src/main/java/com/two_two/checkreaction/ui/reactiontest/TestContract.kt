package com.two_two.checkreaction.ui.reactiontest

import android.content.Context

import com.two_two.checkreaction.models.game.TestResult
import com.two_two.checkreaction.models.game.TestType

/**
 * Created by Dmitry Borodin on 1/3/2016.
 */
interface TestContract {

    interface View {
        fun setWait(testNumber: Int, maxTests: Int)
        fun setReadyToTouch(backgroundColor: Int)
        fun showResult(result: TestResult)
    }

    interface Presenter {
        fun initialize(testType: TestType)
        fun viewTouched()
        fun bindView(activity: View)
        fun unbindView()
    }
}
