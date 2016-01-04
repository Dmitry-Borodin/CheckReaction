package com.two_two.checkreaction.startscreen;

import com.two_two.checkreaction.models.TestType;

import java.util.ArrayList;

/**
 * Created by Dmitry Borodin on 1/3/2016.
 */
public class StartScreenPresenter {

    private static volatile StartScreenPresenter sInstance;
    private ArrayList<StartActivityContract> mCurrentActivity = new ArrayList<>(2);

    public static StartScreenPresenter getInstance() {
        StartScreenPresenter instance = sInstance;
        if (instance == null) {
            synchronized (StartScreenPresenter.class) {
                instance = sInstance;
                if (instance == null) {
                    sInstance = instance = new StartScreenPresenter();
                }
            }
        }
        return instance;
    }

    private StartScreenPresenter() {}

    // Section: Methods for view

    public void registerActivity(StartActivityContract activity) {
        mCurrentActivity.add(activity);
    }

    public void unregisterActivity(StartActivityContract activity) {
        mCurrentActivity.remove(activity);
    }

    public void startSimpleTest(){
        notifyStartTest(TestType.SIMPLE_TEST);
    }

    public void startComplexTest(){
        notifyStartTest(TestType.COMPLEX_TEST);
    }

    private void notifyStartTest(TestType testType) {
        for (StartActivityContract activity:mCurrentActivity) {
            activity.startTest(testType);
        }
    }
}
