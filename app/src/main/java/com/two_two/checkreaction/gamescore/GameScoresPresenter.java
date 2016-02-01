package com.two_two.checkreaction.gamescore;

import com.two_two.checkreaction.startscreen.StartActivityContract;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */
public class GameScoresPresenter implements GameScoresContract.ScoresPresenter {

    private static volatile GameScoresPresenter sInstance;
    private Set<GameScoresContract.ScoresView> mCurrentActivity = new HashSet<>(2);

    public static GameScoresPresenter getInstance() {
        GameScoresPresenter instance = sInstance;
        if (instance == null) {
            synchronized (GameScoresPresenter.class) {
                instance = sInstance;
                if (instance == null) {
                    sInstance = instance = new GameScoresPresenter();
                }
            }
        }
        return instance;
    }

    private GameScoresPresenter() {}

    // Section: Methods for view

    @Override
    public void registerActivity(GameScoresContract.ScoresView activity) {
        mCurrentActivity.add(activity);
    }

    @Override
    public void unregisterActivity(GameScoresContract.ScoresView activity) {
        mCurrentActivity.remove(activity);
    }
}
