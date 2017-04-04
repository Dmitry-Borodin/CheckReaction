package com.two_two.checkreaction.ui.reactiontest

import android.util.Log
import com.two_two.checkreaction.di.DependencyProvider
import com.two_two.checkreaction.domain.game.ReactionTest
import com.two_two.checkreaction.models.game.TestResult
import com.two_two.checkreaction.models.game.TestType
import com.two_two.checkreaction.utils.ColorGenerator

/**
 * Created by Dmitry Borodin on 1/4/2016.
 *
 *  Is singleton just because I'm too lazy to save it's state when view recreates.
 */
class TestPresenter private constructor() : TestContract.Presenter, ReactionTest.ReactionTestCallback {

    companion object {
        private const val TAG = "TestPresenter"
        val instance: TestPresenter by lazy { TestPresenter() }
    }

    private var reactionTest: ReactionTest? = null
    private var colorGenerator: ColorGenerator? = null
    private var view: TestContract.View? = null

    //*******************************************************
    // Section: TestContract.Presenter
    //*******************************************************

    override fun initialize(testType: TestType) {
        reactionTest = ReactionTest(testType)
        reactionTest?.startTest(this)
        colorGenerator = DependencyProvider.getColourGenerator()
    }

    override fun viewTouched() {
        if (reactionTest == null) {
            Log.e(TAG, "Reaction test is NULL in presenter")
            return
        }else {
            reactionTest?.onTap()
        }
    }

    override fun bindView(activity: TestContract.View) {
        view = activity
    }

    override fun unbindView() {
        view = null
    }

    //*******************************************************
    // Section: ReactionTest.ReactionTestCallback
    //*******************************************************


    override fun onReadyForNextTap() {
        val color = colorGenerator!!.nextColor
        view?.setReadyToTouch(color)
    }

    override fun onTestFinished(result: TestResult) {
        view?.showResult(result)
        reactionTest = null
    }

    override fun onWaitForNextTest(currentAttampt: Int, maxAttempts: Int) {
        view?.setWait(currentAttampt, maxAttempts)
    }
}
