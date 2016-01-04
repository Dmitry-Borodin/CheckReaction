package com.two_two.checkreaction.models;

import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Borodin on 1/3/2016.
 */
public final class ReactionTest {

    //*******************************************************
    // Section: values
    //*******************************************************
    private static final String TAG = "ReactionTest";
    private final TestType mTestType;
    private boolean mRunned = false;
    private boolean isTapWaited = false;
    private DelayTimer mDelayTimer = new DelayTimer();
    private UiPresenter mPresenter;
    private long mCurrentTime;
    private int mIteration = 0;
    private List<Long> mResultList = new ArrayList<>();

    //*******************************************************
    // Section: constructor-getters-setters
    //*******************************************************

    public ReactionTest(TestType testType) {
        this.mTestType = testType;
    }

    public TestType getTestType() {
        return mTestType;
    }

    //*******************************************************
    // Section: public
    //*******************************************************
    public void startTest(UiPresenter presenter) {
        if (mRunned) {
            throw new RuntimeException("startTest called but already run");
        }
        mPresenter = presenter;
        mRunned = true;
        nextTest();
        mPresenter.waitForNextTest(mIteration, mTestType.getMaxAttempts());
    }

    public void onTap() {
        if (!mRunned) {
            Log.e(TAG, "onTap called, but test did not run");
            return;
        }
        if (isTapWaited) {
            onTapWaited();
        }else {
            buildFailResults();
        }
    }

    private void onTapWaited() {
        long reaction = System.currentTimeMillis() - mCurrentTime;
        mResultList.add(reaction);
        mIteration++;
        if (mIteration < mTestType.getMaxAttempts()) {
            nextTest();
            mPresenter.waitForNextTest(mIteration, mTestType.getMaxAttempts());
        } else {
           buildSuccessResults();
        }
    }

    private void nextTest() {
        mDelayTimer.runDelayed(() -> {
            mCurrentTime = System.currentTimeMillis();
            mPresenter.waitingForTap();
            isTapWaited = true;
        });
    }

    private void buildFailResults() {
        mRunned = false;
        long avg = 0; //test filed, no results
        boolean isFailed = true;
        mPresenter.testFinished(new TestResult(avg, isFailed, mTestType));
    }

    private void buildSuccessResults() {
        mRunned = false;
        long avg = calcAverageReaction();
        mPresenter.testFinished(new TestResult(avg, false, mTestType));
    }

    private long calcAverageReaction() {
        long avg = 0;
        for (Long l: mResultList) avg+=l;
        avg = avg / mResultList.size();
        return avg;
    }

    //*******************************************************
    // Section: Helpers
    //*******************************************************

    public interface UiPresenter {
        void waitingForTap();
        void testFinished(TestResult result);
        void waitForNextTest(int Iteration, int maxAttempts);
    }

}
