package com.two_two.checkreaction.gamescore;

import android.app.Activity;
import android.os.Bundle;

import com.two_two.checkreaction.R;

/**
 * Created by Dmitry Borodin on 2/1/2016.
 */
public class GameScoreActivity extends Activity implements GameScoresContract.ScoresView {

    private GameScoresContract.ScoresPresenter mPresenter = GameScoresPresenter.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_scores);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.registerActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unregisterActivity(this);
    }
}
