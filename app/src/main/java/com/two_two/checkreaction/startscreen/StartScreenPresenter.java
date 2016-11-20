package com.two_two.checkreaction.startscreen;

import com.two_two.checkreaction.model.App;
import com.two_two.checkreaction.model.game.TestType;

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

    private StartScreenPresenter() {
    }

    // Section: Methods for view

    public void registerActivity(StartActivityContract activity) {
        mCurrentActivity.add(activity);
    }

    public void unregisterActivity(StartActivityContract activity) {
        mCurrentActivity.remove(activity);
    }

    public void startSimpleTest() {
        notifyStartTest(TestType.SIMPLE_TEST);
    }

    public void startComplexTest() {
        notifyStartTest(TestType.COMPLEX_TEST);
    }

    /**
     * If username is valid, this will be used in global score results in the cloud.
     * If not valid, nothing happens, previous or default name will remain.
     *
     * @param username name to save
     */
    public void saveUsername(String username) {
        if (isUsernameCurrect(username)) App.getInstance().getLocalData().setUsername(username);
    }

    private boolean isUsernameCurrect(String username) {
        if (username.isEmpty() || username.length() > 100) return false;
        return true;
    }

    private void notifyStartTest(TestType testType) {
        for (StartActivityContract activity : mCurrentActivity) {
            activity.startTest(testType);
        }
    }
}
