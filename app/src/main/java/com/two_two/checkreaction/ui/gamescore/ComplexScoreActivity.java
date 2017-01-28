package com.two_two.checkreaction.ui.gamescore;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.two_two.checkreaction.BuildConfig;
import com.two_two.checkreaction.R;
import com.two_two.checkreaction.models.game.FirebaseComplexResult;

/**
 * Showing gamescores for Complex Test from Firebase backend.
 * Created by Dmitry Borodin on 2/1/2016.
 */
public class ComplexScoreActivity extends Activity {

    private ListView mList;
    private ComplexScoresListAdapter mAdapter;
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
        FirebaseComplexResult currentResult = getIntent().getParcelableExtra(FirebaseComplexResult.TAG);
        mFirebasecores = new Firebase(BuildConfig.FIREBASE_ROOT)
                .child(BuildConfig.FIREBASE_GAMESCORES);
        mAdapter = new ComplexScoresListAdapter(this, mFirebasecores.orderByChild(FirebaseComplexResult.MEDIAN),
                currentResult, dialog::dismiss);
        mList.setAdapter(mAdapter);
    }

}
