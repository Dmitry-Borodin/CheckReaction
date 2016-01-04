package com.two_two.checkreaction.finishscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.two_two.checkreaction.R;
import com.two_two.checkreaction.models.TestResult;
import com.two_two.checkreaction.models.TestType;
import com.two_two.checkreaction.reactiontest.TestActivity;


public class FinishActivity extends Activity {

    private TextView mResultView;
    private TestResult mTestResult;
    private static final String TAG = "FinishActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        mResultView = (TextView)findViewById(R.id.ac_finish_result_text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTestResult = getIntent().getParcelableExtra(TestResult.TAG);
        if (mTestResult.isFailed()){
            mResultView.setText(getString(R.string.Too_fast));
        } else {
            switch (mTestResult.getTestType()){
                case SIMPLE_TEST: mResultView.setText(getString(R.string.simple_reaction_is) + mTestResult.getAverage() + getString(R.string.ms));
                    break;
                case COMPLEX_TEST: mResultView.setText(getString(R.string.complex_reaction_is) + mTestResult.getAverage() + getString(R.string.ms));
                    break;
                default:
                    Log.e(TAG,"error in onTouch switch - default working");
                    mResultView.setText(getString(R.string.ErrorInTestTypeSwitch));
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    public void againMethod(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra(TestType.TAG, mTestResult.getTestType());
        startActivity(intent);
    }
}

