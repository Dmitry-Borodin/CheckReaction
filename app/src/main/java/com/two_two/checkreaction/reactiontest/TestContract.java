package com.two_two.checkreaction.reactiontest;

import android.content.Context;

import com.two_two.checkreaction.models.ReactionTest;
import com.two_two.checkreaction.models.TestResult;
import com.two_two.checkreaction.models.TestType;

/**
 * Created by Dmitry Borodin on 1/3/2016.
 */
public interface TestContract {

    public interface View {
        void setWait(int testNumber, int maxTests);
        void setReadyToTouch(int backgroundColor);
        void showResult(TestResult result);
    }

    public interface Presenter {
        void initialize(TestType test, Context context);
        void viewTouched();
        void registerActivity(View activity);
        void unregisterActivity(View activity);
    }
}
