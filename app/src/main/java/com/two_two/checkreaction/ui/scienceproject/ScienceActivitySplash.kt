package com.two_two.checkreaction.ui.scienceproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.two_two.checkreaction.R

class ScienceActivitySplash : AppCompatActivity() {

    @BindView(R.id.science_splash_next_button)
    internal var nextButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_splash)
        ButterKnife.bind(this)
        nextButton?.setOnClickListener { startScienceTest() }
    }

    fun startScienceTest() {
        intent = Intent(this, ScienceTestActivity::class.java)
        startActivity(intent)
    }
}
