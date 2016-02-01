package com.two_two.checkreaction.gamescore;

import android.app.Activity;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.two_two.checkreaction.models.firebase.FirebaseComplexResult;

/**
 * Adapter for score list by Firebase.
 */
public class ScoresListAdapter extends FirebaseListAdapter<FirebaseComplexResult> {

    public ScoresListAdapter(Activity activity, Class<FirebaseComplexResult> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
    }

}
