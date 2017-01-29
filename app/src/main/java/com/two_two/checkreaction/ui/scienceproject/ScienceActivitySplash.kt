package com.two_two.checkreaction.ui.scienceproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.two_two.checkreaction.R
import com.two_two.checkreaction.domain.science.ScienceTargetGenerator
import com.two_two.checkreaction.domain.science.colors.ColourProvider
import com.two_two.checkreaction.ui.gamescore.science.ScienceScoreActivity
import kotlinx.android.synthetic.main.activity_science_splash.*

class ScienceActivitySplash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_splash)
    }

    override fun onStart() {
        super.onStart()
        science_splash_next_button.setOnClickListener { startScienceTest() }
        science_splash_leaderboard_button.setOnClickListener { showLeaderboard() }
        val targetColor = ScienceTargetGenerator.chosenColorIndex
        val colourProvider = ColourProvider()
        chosen_colour_text.setTextColor(colourProvider.getColorId(targetColor))
        chosen_colour_text.text = getString(R.string.chosenTextWillBe) + colourProvider.getColourName(targetColor)
    }

    fun showLeaderboard() {
        intent = Intent(this, ScienceScoreActivity::class.java)
        startActivity(intent)
    }
    fun startScienceTest() {
        intent = Intent(this, ScienceTestActivity::class.java)
        startActivity(intent)
    }
}
