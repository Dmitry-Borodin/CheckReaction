package com.two_two.checkreaction.models.game;

import android.util.Log;

import com.two_two.checkreaction.models.LocalData;

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
    private boolean mIsTapWaited = false;
    private DelayTimer mDelayTimer = new DelayTimer();
    private UiPresenter mPresenter;
    private long mCurrentTime;
    private int mIteration = 1;
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
        if (mIsTapWaited) {
            onTapWaited();
            mIsTapWaited = false;
        }else {
            buildFailResults();
        }
    }

    private void onTapWaited() {
        long reaction = System.currentTimeMillis() - mCurrentTime;
        mResultList.add(reaction);
        mIteration++;
        if (mIteration <= mTestType.getMaxAttempts()) {
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
            mIsTapWaited = true;
        });
    }

    private void buildFailResults() {
        mRunned = false;
        long noResult = 0; //test filed, no results
        boolean isFailed = true;
        mPresenter.testFinished(new TestResult(noResult, noResult, isFailed, mTestType));
    }

    private void buildSuccessResults() {
        mRunned = false;
        long avg = calcAverageReaction();
        long median = calcMedianReaction();
        boolean noFailed = false;
        mPresenter.testFinished(new TestResult(avg, median, noFailed, mTestType));
    }

    private long calcAverageReaction() {
        if (mResultList.size() == 0) return 0;
        long avg = 0;
        for (Long l: mResultList) avg+=l;
        avg = avg / mResultList.size();
        return avg;
    }

    //If there is middle element - it is median, else - average for 2 middle elements
    private long calcMedianReaction() {
        if (mResultList.size() == 0) return 0;
        if (mResultList.size()%2 == 1) {
            return mResultList.get(mResultList.size() / 2);
        }else {
            long m1 = mResultList.get(mResultList.size() / 2);
            long m2 = mResultList.get(mResultList.size() / 2 - 1);
            return (m1 + m2) / 2;
        }
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
