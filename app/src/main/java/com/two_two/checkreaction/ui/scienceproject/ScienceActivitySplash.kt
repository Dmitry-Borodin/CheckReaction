package com.two_two.checkreaction.ui.scienceproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.two_two.checkreaction.R
import com.two_two.checkreaction.databinding.ActivityScienceSplashBinding
import com.two_two.checkreaction.domain.science.ScienceTargetGenerator
import com.two_two.checkreaction.domain.science.colors.ColourProvider

class ScienceActivitySplash : Activity() {
    private lateinit var binding: ActivityScienceSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_splash)
        binding = ActivityScienceSplashBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        binding.scienceSplashNextButton.setOnClickListener { startScienceTest() }
//        science_splash_leaderboard_button.setOnClickListener { showLeaderboard() }
        val targetColor = ScienceTargetGenerator.chosenColorIndex
        val colourProvider = ColourProvider()
        binding.chosenColourText.setTextColor(colourProvider.getColorId(targetColor))
        binding.chosenColourText.text = getString(R.string.chosenTextWillBe) + colourProvider.getColourName(targetColor)
    }

//    fun showLeaderboard() {
//        intent = Intent(this, ScienceScoreActivity::class.java)
//        startActivity(intent)
//    }

    fun startScienceTest() {
        intent = Intent(this, ScienceTestActivity::class.java)
        startActivity(intent)
    }
}
