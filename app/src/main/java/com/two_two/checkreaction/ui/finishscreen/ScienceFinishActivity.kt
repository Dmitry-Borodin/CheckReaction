package com.two_two.checkreaction.ui.finishscreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.two_two.checkreaction.R
import com.two_two.checkreaction.databinding.ActivityScienceFinishBinding
import com.two_two.checkreaction.models.App
import com.two_two.checkreaction.models.firebase.FirebaseScienceResult
import com.two_two.checkreaction.models.science.ScienceTestResult
import java.io.Serializable

class ScienceFinishActivity : Activity() {
    private lateinit var binding: ActivityScienceFinishBinding
    var testResult: ScienceTestResult? = null
    var firebaseResult : FirebaseScienceResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_finish)
        binding = ActivityScienceFinishBinding.inflate(layoutInflater)

        testResult = intent.getSerializableExtra(ScienceTestResult.TAG) as ScienceTestResult?
        if (testResult == null) {
            binding.accuracyResultText.setText(R.string.error_cannot_find_result)
            binding.accuracyResultText.visibility = View.INVISIBLE
        } else {
            calcFirebaseTestResult()
            fillTextForResult()
//            updateScienceTestResult()
        }
//        rating_button.setOnClickListener { openResultsScreen() }
    }

    private fun calcFirebaseTestResult() {
        val username = App.instance.localData?.username
        firebaseResult = FirebaseScienceResult(testResult!!.currectHits, testResult!!.average, username!!)
    }

    private fun ScienceFinishActivity.fillTextForResult() {
        val accuracySb = StringBuilder()
        accuracySb.append(getString(R.string.accuracy))
        accuracySb.append(testResult?.currectHits)
        accuracySb.append(getString(R.string.of))
        accuracySb.append(testResult?.testType?.maxAttempts)
        accuracySb.append(getString(R.string.tapped_ok))
        binding.accuracyResultText.text = accuracySb.toString()
        val averageSB = StringBuilder()
        averageSB.append(getString(R.string.speed))
        averageSB.append(testResult?.average)
        averageSB.append(getString(R.string.milliseconds_descroption))
        binding.averageResultText.text = averageSB.toString()
    }

//    private fun openResultsScreen() {
//        intent = Intent(this, ScienceScoreActivity::class.java)
//        intent.putExtra(FirebaseScienceResult.TAG, firebaseResult as Serializable)
//        startActivity(intent)
//    }

//    private fun updateScienceTestResult() {
//        val fireResult = firebaseResult ?: return
//        FirebaseSender.getInstance().updateScienceTestResult(fireResult)
//    }
}
