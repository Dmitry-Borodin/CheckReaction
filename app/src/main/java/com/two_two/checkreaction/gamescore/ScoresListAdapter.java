package com.two_two.checkreaction.gamescore;

import android.app.Activity;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.two_two.checkreaction.models.firebase.FirebaseComplexTestResult;

/**
 * Adapter for score list by Firebase.
 */
public class ScoresListAdapter extends FirebaseListAdapter<FirebaseComplexTestResult> {

    public ScoresListAdapter(Activity activity, Class<FirebaseComplexTestResult> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
    }

}
