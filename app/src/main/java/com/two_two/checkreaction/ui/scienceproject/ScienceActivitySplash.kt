package com.two_two.checkreaction.ui.scienceproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.two_two.checkreaction.R
import kotlinx.android.synthetic.main.activity_science_splash.*

class ScienceActivitySplash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_science_splash)
    }

    override fun onStart() {
        super.onStart()
        science_splash_next_button.setOnClickListener { startScienceTest() }
    }

    fun startScienceTest() {
        intent = Intent(this, ScienceTestActivity::class.java)
        startActivity(intent)
    }
}
