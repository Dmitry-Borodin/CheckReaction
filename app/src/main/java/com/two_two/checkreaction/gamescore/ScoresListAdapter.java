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

    public ScoresListAdapter(Activity activity, Class<FireComplexResult> modelClass,
                             int modelLayout, Query ref,
                             FireComplexResult currentResult) {
        super(activity, modelClass, modelLayout, ref);
        this.mCurrentResult = currentResult;
    }

    @Override
    protected void populateView(View v, FireComplexResult model, int position) {

        TextView number = (TextView) v.findViewById(R.id.item_score_number);
        TextView username = (TextView) v.findViewById(R.id.item_score_username);
        TextView average = (TextView) v.findViewById(R.id.item_score_average);
        TextView median = (TextView) v.findViewById(R.id.item_score_median);

        if (model.equals(mCurrentResult)) v.setBackgroundColor(Color.RED);

        int oneStartedPosition = position + 1; //winner should not have zero position
        number.setText("" + oneStartedPosition);
        username.setText(model.getUsername());
        average.setText("" + model.getAverage());
        median.setText("" + model.getMedian());
    }
}
