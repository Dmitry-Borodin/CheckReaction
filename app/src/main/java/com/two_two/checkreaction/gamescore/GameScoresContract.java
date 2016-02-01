package com.two_two.checkreaction.gamescore;

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */
public interface GameScoresContract {
    interface ScoresView {

    }

    interface ScoresPresenter {
        void registerActivity(ScoresView activity);
        void unregisterActivity(ScoresView activity);
    }
}
