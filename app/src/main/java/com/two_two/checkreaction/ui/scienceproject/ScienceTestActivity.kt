package com.two_two.checkreaction.ui.scienceproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.science.ScienceTestResult
import com.two_two.checkreaction.ui.finishscreen.ScienceFinishActivity
import kotlinx.android.synthetic.main.activity_science_test.*
import java.io.Serializable

class ScienceTestActivity : Activity(), ScienceTestContract.View {

    private val presenter = ScienceTestPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_test)
        science_tile1.setOnClickListener { presenter.onFirstViewClicked() }
        science_tile2.setOnClickListener { presenter.onSecondViewClicked() }
        science_tile3.setOnClickListener { presenter.onThirdViewClicked() }
        science_tile4.setOnClickListener { presenter.onForthViewClicked() }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unBindView()
    }

    override fun setFirstViewColour(color: Int) {
        science_tile1.setBackgroundColor(color)
    }

    override fun setSecondViewColour(color: Int) {
        science_tile2.setBackgroundColor(color)
    }

    override fun setThirdViewColour(color: Int) {
        science_tile3.setBackgroundColor(color)
    }

    override fun setForthViewColour(color: Int) {
        science_tile4.setBackgroundColor(color)
    }

    override fun setTargetColour(color: Int, colorName: String) {
        header_advice.setTextColor(color)
        header_advice.text = getString(R.string.science_test_header1) + colorName + getString(R.string.science_test_header2)
    }

    override fun navigateToResults(result: ScienceTestResult) {
        val intent = Intent(this, ScienceFinishActivity::class.java)
        intent.putExtra(ScienceTestResult.TAG, result as Serializable)
        startActivity(intent)
        finish()
    }
}
