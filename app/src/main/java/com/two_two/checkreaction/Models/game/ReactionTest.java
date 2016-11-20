package com.two_two.checkreaction.models.game;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for reaction test itself.
 */
public final class ReactionTest {

    private static final String TAG = "ReactionTest";
    private final TestType mTestType;
    private boolean mIsRunning;
    private boolean mIsReadyForNextTap;
    private final DelayTimer mDelayTimer = new DelayTimer();
    private ReactionTestCallback mTestCallback;
    private long mCurrentTime;
    private int mIteration;
    private List<Long> mResultList = new ArrayList<>();

    public ReactionTest(TestType testType) {
        this.mTestType = testType;
        mIteration = 1;
        mIsRunning = false;
        mIsReadyForNextTap = false;
    }

    public TestType getTestType() {
        return mTestType;
    }

    public void startTest(ReactionTestCallback testCallback) {
        if (mIsRunning) {
            throw new RuntimeException("startTest called, but already run");
        }
        mTestCallback = testCallback;
        mIsRunning = true;
        startTestIteration();
        mTestCallback.waitForNextTest(mIteration, mTestType.getMaxAttempts());
    }

    public void onTap() {
        if (!mIsRunning) {
            Log.e(TAG, "onTap called, but test did not run");
            return;
        }
        if (mIsReadyForNextTap) {
            onIterationSucceed();
            mIsReadyForNextTap = false;
        } else {
            proceedFailResults();
        }
    }

    private void onIterationSucceed() {
        long reaction = System.currentTimeMillis() - mCurrentTime;
        mResultList.add(reaction);
        mIteration++;
        if (mIteration <= mTestType.getMaxAttempts()) {
            startTestIteration();
            mTestCallback.waitForNextTest(mIteration, mTestType.getMaxAttempts());
        } else {
            proceedSuccessResults();
        }
    }

    private void startTestIteration() {
        mDelayTimer.runDelayed(() -> {
            mCurrentTime = System.currentTimeMillis();
            mTestCallback.onReadyForNextTap();
            mIsReadyForNextTap = true;
        });
    }

    private void proceedFailResults() {
        mIsRunning = false;
        long noResult = 0; //test filed, no results
        boolean isFailed = true;
        mTestCallback.onTestFinished(new TestResult(noResult, noResult, isFailed, mTestType));
    }

    private void proceedSuccessResults() {
        mIsRunning = false;
        long avg = calcAverageReaction();
        long median = calcMedianReaction();
        boolean isFailed = false;
        mTestCallback.onTestFinished(new TestResult(avg, median, isFailed, mTestType));
    }

    private long calcAverageReaction() {
        if (mResultList.size() == 0) return 0;
        long resultSumm = 0;
        for (Long result : mResultList) resultSumm += result;
        long avg = resultSumm / mResultList.size();
        return avg;
    }

    //If there is middle element - it is median, else - average for 2 middle elements
    private long calcMedianReaction() {
        if (mResultList.size() == 0) return 0;
        if (mResultList.size() % 2 == 1) {
            return mResultList.get(mResultList.size() / 2);
        } else {
            long m1 = mResultList.get(mResultList.size() / 2);
            long m2 = mResultList.get(mResultList.size() / 2 - 1);
            return (m1 + m2) / 2;
        }
    }

    //*******************************************************
    // Section: Helpers
    //*******************************************************

    public interface ReactionTestCallback {
        void onReadyForNextTap();

        void onTestFinished(TestResult result);

        void waitForNextTest(int currentAttampt, int maxAttempts);
    }

}
