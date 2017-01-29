package com.two_two.checkreaction.ui.scienceproject

import android.content.Context
import com.two_two.checkreaction.di.DependencyProvider
import com.two_two.checkreaction.models.App

/**
 * @author Dmitry Borodin on 2017-01-29.
 */
class ScienceTestPresenter(val appContext: Context? = App.getInstance()) : ScienceTestContract.Presenter {

    var view: ScienceTestContract.View? = null
    val scienceTest = DependencyProvider.getScienceTest()


    override fun bindActivity(view: ScienceTestContract.View) {
        this.view = view
    }

    override fun unBindActivity() {
        view = null
    }

    override fun onFirstViewClicked() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSecondViewClicked() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onThirdViewClicked() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onForthViewClicked() {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}