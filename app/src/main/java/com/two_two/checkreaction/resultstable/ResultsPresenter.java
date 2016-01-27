package com.two_two.checkreaction.resultstable;

import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.two_two.checkreaction.models.game.ParseTestResult;
import com.two_two.checkreaction.startscreen.StartActivityContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */
public class ResultsPresenter {
    private static volatile ResultsPresenter sInstance;
    private ArrayList<StartActivityContract> mCurrentActivity = new ArrayList<>(2);

    public static ResultsPresenter getInstance() {
        ResultsPresenter instance = sInstance;
        if (instance == null) {
            synchronized (ResultsPresenter.class) {
                instance = sInstance;
                if (instance == null) {
                    sInstance = instance = new ResultsPresenter();
                }
            }
        }
        return instance;
    }

    private ResultsPresenter() {}

    // Section: Methods for view

    public void registerActivity(StartActivityContract activity) {
        mCurrentActivity.add(activity);
    }

    public void unregisterActivity(StartActivityContract activity) {
        mCurrentActivity.remove(activity);
    }

    public List<ParseTestResult> getResults () {
        ParseQuery<ParseTestResult> query = new ParseQuery<ParseTestResult>();
        query.whereGreaterThan()
                .where
    }

}
