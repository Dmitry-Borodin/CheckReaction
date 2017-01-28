package com.two_two.checkreaction.ui.scienceproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.science.ScienceTestResult
import com.two_two.checkreaction.ui.finishscreen.FinishActivity
import kotlinx.android.synthetic.main.activity_science_test.*
import java.io.Serializable

class ScienceTestActivity : Activity(), ScienceTestContract.View {

    private val presenter = ScienceTestPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_test)
    }

    override fun onStart() {
        super.onStart()
        presenter.bindActivity(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBindActivity()
    }

    override fun setFirstViewColor(color: Int) {
    }

    override fun setSecondViewColor(color: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setThirdViewColor(color: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setForthViewColor(color: Int) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setRightColor(color: Int, colorName: String) {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToResults(result: ScienceTestResult) {
        val intent = Intent(this, FinishActivity::class.java)
        intent.putExtra(ScienceTestResult.TAG, result as Serializable)
        startActivity(intent)
        finish()
    }

}
