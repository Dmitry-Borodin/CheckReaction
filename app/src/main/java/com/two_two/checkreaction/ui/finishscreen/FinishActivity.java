package com.two_two.checkreaction.ui.finishscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.two_two.checkreaction.R;
import com.two_two.checkreaction.ui.gamescore.GameScoreActivity;
import com.two_two.checkreaction.domain.App;
import com.two_two.checkreaction.domain.firebase.FireComplexResult;
import com.two_two.checkreaction.domain.firebase.FirebaseSender;
import com.two_two.checkreaction.domain.game.TestResult;
import com.two_two.checkreaction.domain.game.TestType;
import com.two_two.checkreaction.ui.reactiontest.TestActivity;


public class FinishActivity extends Activity {

    private TextView mResultView;
    private TestResult mTestResult;
    private Button mRatingButton;
    private FireComplexResult mFireResult;
    private static final String TAG = "FinishActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        mResultView = (TextView) findViewById(R.id.ac_finish_result_text);
        mRatingButton = (Button) findViewById(R.id.ac_finish_rating_button);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTestResult = getIntent().getParcelableExtra(TestResult.TAG);
        if (mTestResult == null) {
            mResultView.setText(R.string.error_cannot_find_result);
        } else if (mTestResult.isFailed()) {
            mResultView.setText(getString(R.string.Too_fast));
            showRatingButtonIfNeeded();
        } else {
            switch (mTestResult.getTestType()) {
                case SIMPLE_TEST:
                    mResultView.setText(getString(R.string.simple_reaction_is) + mTestResult.getAverage() + getString(R.string.ms));
                    break;
                case COMPLEX_TEST:
                    showRatingButtonIfNeeded();
                    mResultView.setText(getString(R.string.complex_reaction_is) +
                            mTestResult.getAverage() + getString(R.string.ms)
                            + getString(R.string.ac_finish_your_median_result) +
                            mTestResult.getMedian() + getString(R.string.ms));
                    updateComplexTestResult();
                    break;
                default:
                    Log.e(TAG, "error in onTouch switch - default working");
                    mResultView.setText(getString(R.string.ErrorInTestTypeSwitch));
            }
        }
    }

    private void showRatingButtonIfNeeded() {
        if (mTestResult == null) return;
        if (mTestResult.getTestType().equals(TestType.COMPLEX_TEST)) {
            mRatingButton.setVisibility(View.VISIBLE);
        }
    }

    private void updateComplexTestResult() {
        final String username = App.getInstance().getLocalData().getUsername();
        mFireResult = new FireComplexResult(mTestResult.getAverage(),
                mTestResult.getMedian(), username);
        FirebaseSender.getInstance().updateResult(mFireResult);
    }

    public void againMethod(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        intent.putExtra(TestType.TAG, mTestResult.getTestType());
        startActivity(intent);
    }

    //  defined in XML
    public void toRaiting(View view) {
        Intent intent = new Intent(this, GameScoreActivity.class);
        intent.putExtra(FireComplexResult.TAG, mFireResult);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent.hasExtra(TestResult.TAG)) {
            setIntent(intent);
        }
    }
}

