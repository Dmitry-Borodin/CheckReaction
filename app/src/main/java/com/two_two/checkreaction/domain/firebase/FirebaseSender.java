package com.two_two.checkreaction.domain.firebase;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.firebase.client.annotations.NotNull;
import com.two_two.checkreaction.BuildConfig;
import com.two_two.checkreaction.models.game.FirebaseComplexResult;
import com.two_two.checkreaction.models.science.FirebaseScienceResult;
import com.two_two.checkreaction.utils.Constants;


public class FirebaseSender {

    private static volatile FirebaseSender instance;
    private FirebaseComplexResult complexTestResult;
    private FirebaseScienceResult scienceResult;

    public static FirebaseSender getInstance() {
        FirebaseSender instance = FirebaseSender.instance;
        if (instance == null) {
            synchronized (FirebaseSender.class) {
                instance = FirebaseSender.instance;
                if (instance == null) {
                    FirebaseSender.instance = instance = new FirebaseSender();
                }
            }
        }
        return instance;
    }

    /**
     * Adds new record score to remote database if needed.
     */
    public void updateComplexTestResult(@NotNull FirebaseComplexResult testResult) {
        if (complexTestResult != null && complexTestResult.equals(testResult)) {
            return;
        }
        complexTestResult = testResult;

        Firebase fb = new Firebase(BuildConfig.FIREBASE_ROOT).child(BuildConfig.FIREBASE_GAMESCORES);
        fb = fb.push(); //new line in db
        fb.setValue(testResult);
        fb.child(Constants.TIMESTAMP).setValue(ServerValue.TIMESTAMP);
    }

    public void updateScienceTestResult(@NotNull FirebaseScienceResult testResult) {
        if (scienceResult != null && scienceResult.equals(testResult)) {
            return;
        }
        scienceResult = testResult;

        Firebase fb = new Firebase(BuildConfig.FIREBASE_ROOT).child(BuildConfig.SCIENCE_GAMESCORES);
        fb = fb.push(); //new line in db
        fb.setValue(testResult);
        fb.child(Constants.TIMESTAMP).setValue(ServerValue.TIMESTAMP);
    }
}
