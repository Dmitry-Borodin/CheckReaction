package com.two_two.checkreaction.gamescore;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.two_two.checkreaction.BuildConfig;
import com.two_two.checkreaction.R;
import com.two_two.checkreaction.models.firebase.FireComplexResult;

/**
 * Showing gamescores from Firebase backend.
 * Created by Dmitry Borodin on 2/1/2016.
 */
public class GameScoreActivity extends Activity {

    private ListView mList;
    private ScoresListAdapter mAdapter;
    private Firebase mFirebasecores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_scores);
        init();
    }

    private void init() {
        ProgressDialog dialog = ProgressDialog.show(this, getString(R.string.loading_title),
                getString(R.string.loading_message));
        mList = (ListView) findViewById(R.id.ac_scores_list);
        FireComplexResult currentResult = getIntent().getParcelableExtra(FireComplexResult.TAG);
        mFirebasecores = new Firebase(BuildConfig.FIREBASE_ROOT)
                .child(BuildConfig.FIREBASE_GAMESCORES);
        mAdapter = new ScoresListAdapter(this, mFirebasecores.orderByChild(FireComplexResult.MEDIAN),
                currentResult, dialog::dismiss);
        mList.setAdapter(mAdapter);
    }

}
