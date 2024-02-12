package com.two_two.checkreaction.ui.scienceproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.two_two.checkreaction.R
import com.two_two.checkreaction.databinding.ActivityScienceTestBinding
import com.two_two.checkreaction.models.science.ScienceTestResult
import com.two_two.checkreaction.ui.finishscreen.ScienceFinishActivity
import java.io.Serializable

class ScienceTestActivity : Activity(), ScienceTestContract.View {
    private lateinit var binding: ActivityScienceTestBinding

    private val presenter = ScienceTestPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_test)
        binding = ActivityScienceTestBinding.inflate(layoutInflater)
        binding.scienceTile1.setOnClickListener { presenter.onFirstViewClicked() }
        binding.scienceTile2.setOnClickListener { presenter.onSecondViewClicked() }
        binding.scienceTile3.setOnClickListener { presenter.onThirdViewClicked() }
        binding.scienceTile4.setOnClickListener { presenter.onForthViewClicked() }
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
        binding.scienceTile1.setBackgroundColor(color)
    }

    override fun setSecondViewColour(color: Int) {
        binding.scienceTile2.setBackgroundColor(color)
    }

    override fun setThirdViewColour(color: Int) {
        binding.scienceTile3.setBackgroundColor(color)
    }

    override fun setForthViewColour(color: Int) {
        binding.scienceTile4.setBackgroundColor(color)
    }

    override fun setTargetColour(color: Int, colorName: String) {
        binding.headerAdvice.setTextColor(color)
        binding.headerAdvice.text = getString(R.string.science_test_header1) + colorName + getString(R.string.science_test_header2)
    }

    override fun navigateToResults(result: ScienceTestResult) {
        val intent = Intent(this, ScienceFinishActivity::class.java)
        intent.putExtra(ScienceTestResult.TAG, result as Serializable)
        startActivity(intent)
        finish()
    }
}
