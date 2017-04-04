package com.two_two.checkreaction.ui.reactiontest

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.two_two.checkreaction.R
import com.two_two.checkreaction.models.game.TestResult
import com.two_two.checkreaction.models.game.TestType
import com.two_two.checkreaction.ui.finishscreen.FinishActivity

class TestActivity : Activity(), TestContract.View {

    companion object {
        private const val BACKGROUND = "backgroundColor"
    }

    private var presenter: TestPresenter = TestPresenter.instance
    private var textDownSmall: TextView? = null
    private var textInCenter: TextView? = null
    private var layout: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        textDownSmall = findViewById(R.id.ac_test_down_tips) as TextView
        textInCenter = findViewById(R.id.ac_test_center_text) as TextView
        layout = findViewById(R.id.ac_test_main_layout)
        if (savedInstanceState == null) {
            val testType = intent.getSerializableExtra(TestType.TAG) as TestType?
            if (testType == null) {
                Toast.makeText(this, R.string.unknown_test_type, Toast.LENGTH_LONG).show()
                finish()
                return
            }
            presenter.bindView(this)
            presenter.initialize(testType)
        } else {
            @Suppress("DEPRECATION")// it's fallback, new method
            layout?.setBackgroundColor(savedInstanceState.getInt(BACKGROUND, resources
                    .getColor(R.color.default_background)))
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
        layout?.setOnTouchListener { _, event -> onTouched(event) }
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    private fun onTouched(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            presenter.viewTouched()
            return true
        } else {
            return false
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //save current background color
        val background = layout!!.background
        if (background is ColorDrawable) {
            outState.putInt(BACKGROUND, background.color)
        }
        super.onSaveInstanceState(outState)
    }

    //*******************************************************
    // Section: interface - TestContract.View
    //*******************************************************
    override fun setWait(testNumber: Int, maxTests: Int) {
        textInCenter?.text = getString(R.string.testing_string_ready)
        textDownSmall?.text = "Iteration $testNumber from $maxTests"
    }

    override fun setReadyToTouch(backgroundColor: Int) {
        textInCenter?.text = getString(R.string.testing_command_tap)
        layout?.setBackgroundColor(backgroundColor)
    }

    override fun showResult(result: TestResult) {
        val intent = Intent(this, FinishActivity::class.java)
        intent.putExtra(TestResult.TAG, result)
        startActivity(intent)
        finish()
    }
}
