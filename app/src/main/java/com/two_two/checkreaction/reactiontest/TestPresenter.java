package com.two_two.checkreaction.reactiontest;

import android.content.Context;
import android.util.Log;

import com.two_two.checkreaction.utils.ColorGenerator;
import com.two_two.checkreaction.models.game.ReactionTest;
import com.two_two.checkreaction.models.game.TestResult;
import com.two_two.checkreaction.models.game.TestType;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitry Borodin on 1/4/2016.
 */
public class TestPresenter implements TestContract.Presenter, ReactionTest.UiPresenter {

    private static final String TAG = "TestPresenter";
    private static volatile TestPresenter sInstance;
    private ReactionTest mReactionTest;
    private ColorGenerator mColorGenerator;
    private Set<TestContract.View> mActivitySet = new HashSet<>();

    public static TestPresenter getInstance() {
        TestPresenter instance = sInstance;
        if (instance == null) {
            synchronized (TestPresenter.class) {
                instance = sInstance;
                if (instance == null) {
                    sInstance = instance = new TestPresenter();
                }
            }
        }
        return instance;
    }

    private TestPresenter() {}

    //*******************************************************
    // Section: TestContract.Presenter
    //*******************************************************

    @Override
    public void initialize(TestType testType, Context context) {
        mReactionTest = new ReactionTest(testType);
        mReactionTest.startTest(this);
        mColorGenerator = new ColorGenerator(context);
    }

    @Override
    public void viewTouched() {
        if (mReactionTest == null) {
            Log.e(TAG, "Reaction test is NULL in presenter");
            return;
        }
        mReactionTest.onTap();
    }

    @Override
    public void registerActivity(TestContract.View activity) {
        mActivitySet.add(activity);
    }

    @Override
    public void unregisterActivity(TestContract.View activity) {
        mActivitySet.remove(activity);
    }

    //*******************************************************
    // Section: ReactionTest.UiPresenter
    //*******************************************************


    @Override
    public void waitingForTap() {
        int color = mColorGenerator.getNextColor();
        for (TestContract.View activity: mActivitySet) {
            activity.setReadyToTouch(color);
        }
    }

    @Override
    public void testFinished(TestResult result) {
        for (TestContract.View activity: mActivitySet) {
            activity.showResult(result);
        }
        mReactionTest = null;
    }

    @Override
    public void waitForNextTest(int iteration, int maxAttempts) {
        for (TestContract.View activity: mActivitySet) {
            activity.setWait(iteration, maxAttempts);
        }
    }
}
