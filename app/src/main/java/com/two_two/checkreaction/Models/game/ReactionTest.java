package com.two_two.checkreaction.models.game;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for reaction test itself.
 * <p>
 * Call {@link #startTest(ReactionTestCallback)} to start test,
 * And {@link #onTap()} when user provides reaction.
 */
public final class ReactionTest {

    private static final String TAG = "ReactionTest";
    private final TestType mTestType;
    private boolean mIsRunning = false;
    private boolean mIsReadyForNextTap = false;
    private final DelayTimer mDelayTimer = new DelayTimer();
    private ReactionTestCallback mTestCallback;
    private long mStartIterationTime;
    private int mIteration;
    private List<Long> mResultList = new ArrayList<>();

    public ReactionTest(TestType testType) {
        this.mTestType = testType;
    }

    public TestType getTestType() {
        return mTestType;
    }

    public void startTest(ReactionTestCallback testCallback) {
        if (mIsRunning) {
            throw new RuntimeException("startTest called, but already run");
        }
        mIteration = 1;
        mTestCallback = testCallback;
        mIsRunning = true;
        startTestIteration();
    }

    /**
     * Should be called when user actually interact with test.
     */
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
        long reaction = System.currentTimeMillis() - mStartIterationTime;
        mResultList.add(reaction);
        mIteration++;
        if (mIteration <= mTestType.getMaxAttempts()) {
            startTestIteration();
        } else {
            proceedSuccessResults();
        }
    }

    private void startTestIteration() {
        mTestCallback.onWaitForNextTest(mIteration, mTestType.getMaxAttempts());
        mDelayTimer.runDelayed(this::startWaitingForReaction);
    }

    private void startWaitingForReaction() {
        mStartIterationTime = System.currentTimeMillis();
        mTestCallback.onReadyForNextTap();
        mIsReadyForNextTap = true;
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

    @SuppressWarnings("UnnecessaryLocalVariable")
    private long calcAverageReaction() {
        if (mResultList.size() == 0) return 0;
        long resultSumm = 0;
        for (Long result : mResultList) resultSumm += result;
        long avg = resultSumm / mResultList.size();
        return avg;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private long calcMedianReaction() {
        if (mResultList.size() == 0) return 0;
        //If there is middle element - it is median, else - average for 2 middle elements
        if (mResultList.size() % 2 == 1) {
            long median = mResultList.get(mResultList.size() / 2);
            return median;
        } else {
            long m1 = mResultList.get(mResultList.size() / 2);
            long m2 = mResultList.get(mResultList.size() / 2 - 1);
            long averageForTwoMedians = (m1 + m2) / 2;
            return averageForTwoMedians;
        }
    }

    //*******************************************************
    // Section: Helpers
    //*******************************************************

    public interface ReactionTestCallback {
        void onReadyForNextTap();

        void onTestFinished(TestResult result);

        void onWaitForNextTest(int currentAttampt, int maxAttempts);
    }

}
