package com.two_two.checkreaction.reactiontest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.two_two.checkreaction.R;
import com.two_two.checkreaction.finishscreen.FinishActivity;
import com.two_two.checkreaction.models.game.TestResult;
import com.two_two.checkreaction.models.game.TestType;

public class TestActivity extends Activity implements TestContract.View {

    private final static String BACKGROUND = "backgroundColor";
    private TestPresenter mPresenter;
    private TextView mTextDownSmall;
    private TextView mTextInCenter;
    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mPresenter = TestPresenter.getInstance();
        mTextDownSmall = (TextView) findViewById(R.id.ac_test_down_tips);
        mTextInCenter = (TextView) findViewById(R.id.ac_test_center_text);
        mLayout = findViewById(R.id.ac_test_main_layout);
        if (savedInstanceState == null) {
            TestType testType = (TestType) getIntent().getSerializableExtra(TestType.TAG);
            assert (testType != null);
            mPresenter.registerActivity(this);
            mPresenter.initialize(testType, getApplicationContext());
        } else {
            mLayout.setBackgroundColor(savedInstanceState.getInt(BACKGROUND, getResources()
                    .getColor(R.color.default_background)));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.registerActivity(this);
        mLayout.setOnTouchListener(this::onTouch);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unregisterActivity(this);
    }

    private boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mPresenter.viewTouched();
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //save current background color
        Drawable background = mLayout.getBackground();
        if (background instanceof ColorDrawable) {
            outState.putInt(BACKGROUND, ((ColorDrawable) background).getColor());
        }
        super.onSaveInstanceState(outState);
    }

    //*******************************************************
    // Section: interface - TestContract.View
    //*******************************************************
    @Override
    public void setWait(int testNumber, int maxTests) {
        mTextInCenter.setText(getString(R.string.testing_string_ready));
        mTextDownSmall.setText("Iteration " + testNumber + " from " + maxTests);
    }

    @Override
    public void setReadyToTouch(int backgroundColor) {
        mTextInCenter.setText(getString(R.string.testing_command_tap));
        mLayout.setBackgroundColor(backgroundColor);
    }

    @Override
    public void showResult(TestResult result) {
        Intent intent = new Intent(this, FinishActivity.class);
        intent.putExtra(TestResult.TAG, result);
        startActivity(intent);
        finish();
    }
}
