package com.two_two.checkreaction.ui.finishscreen

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.science.ScienceTestResult
import kotlinx.android.synthetic.main.activity_science_finish.*

class ScienceFinishActivity : Activity() {
    var testResult: ScienceTestResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_finish)

        testResult = intent.getSerializableExtra(ScienceTestResult.TAG) as ScienceTestResult
        if (testResult == null) {
            accuracy_result_text.setText(R.string.error_cannot_find_result)
            average_result_text.visibility = View.INVISIBLE
        } else {
            fillTextForResult()
        }
        rating_button.setOnClickListener { openResultsScreen() }
    }

    private fun ScienceFinishActivity.fillTextForResult() {
        val accuracySb = StringBuilder()
        accuracySb.append(getString(R.string.accuracy))
        accuracySb.append(testResult?.currectHits)
        accuracySb.append(getString(R.string.of))
        accuracySb.append(testResult?.testType?.maxAttempts)
        accuracySb.append(getString(R.string.tapped_ok))
        accuracy_result_text.text = accuracySb.toString()
        val averageSB = StringBuilder()
        averageSB.append(getString(R.string.speed))
        averageSB.append(testResult?.average)
        averageSB.append(getString(R.string.milliseconds_descroption))
        average_result_text.text = averageSB.toString()
    }
    
    private fun openResultsScreen() {

    }
}
