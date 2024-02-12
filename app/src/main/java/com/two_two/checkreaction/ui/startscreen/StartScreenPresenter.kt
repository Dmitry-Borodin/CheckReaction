package com.two_two.checkreaction.ui.startscreen

import com.two_two.checkreaction.models.App
import com.two_two.checkreaction.models.game.TestType
import com.two_two.checkreaction.utils.Constants

/**
 * Created by Dmitry Borodin on 1/3/2016.
 */
class StartScreenPresenter() {

    private var mCurrentActivity: StartActivityContract? = null

    fun bindActivity(activity: StartActivityContract) {
        mCurrentActivity = activity
    }

    fun unbindActivity() {
        mCurrentActivity = null
    }

    fun startSimpleTest() {
        startTest(TestType.SIMPLE_TEST)
    }

    fun startComplexTest() {
        startTest(TestType.COMPLEX_TEST)
    }

    fun startScienceTest() {
        startTest(TestType.SCIENCE_TEST)
    }

    /**
     * If username is valid, this will be used in global score results in the cloud.
     * If not valid, nothing happens, previous or default name will remain.

     * @param username name to save
     */
    fun saveUsername(username: String) {
        if (isUsernameCorrect(username)) App.instance.localData?.username = username
    }

    private fun isUsernameCorrect(username: String): Boolean {
        return !(username.isEmpty() || username.length > Constants.USERNAME_LEIGHT_LIMIT)
    }

    private fun startTest(testType: TestType) {
        mCurrentActivity?.startTest(testType)
    }
}
