package com.two_two.checkreaction.gamescore;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.two_two.checkreaction.R;
import com.two_two.checkreaction.models.firebase.FireComplexResult;

/**
 * Adapter for score list by Firebase.
 */
public class ScoresListAdapter extends FirebaseListAdapter<FireComplexResult> {

    private FireComplexResult mCurrentResult;
    private OnLoadListener mOnLoadListener;
    private boolean mWasLoaded = false;

    public ScoresListAdapter(Activity activity, Query ref,
                             FireComplexResult currentResult,
                             OnLoadListener onLoadListener) {
        super(activity, FireComplexResult.class, R.layout.item_score_result, ref);
        this.mCurrentResult = currentResult;
        this.mOnLoadListener = onLoadListener;
    }

    @Override
    protected void populateView(View v, FireComplexResult model, int position) {

        TextView number = (TextView) v.findViewById(R.id.item_score_number);
        TextView username = (TextView) v.findViewById(R.id.item_score_username);
        TextView average = (TextView) v.findViewById(R.id.item_score_average);
        TextView median = (TextView) v.findViewById(R.id.item_score_median);

        if (model.equals(mCurrentResult)) v.setBackgroundColor(Color.RED);
        if (!mWasLoaded) {
            mWasLoaded = true;
            mOnLoadListener.onLoaded();
        }

        int oneStartedPosition = position + 1; //positions from 1, not from 0
        number.setText("" + oneStartedPosition);
        username.setText(model.getUsername());
        average.setText("" + model.getAverage());
        median.setText("" + model.getMedian());
    }
}
