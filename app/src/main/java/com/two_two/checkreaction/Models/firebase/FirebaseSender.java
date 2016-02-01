package com.two_two.checkreaction.models.firebase;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.firebase.client.annotations.NotNull;
import com.two_two.checkreaction.BuildConfig;


public class FirebaseSender {

    private static volatile FirebaseSender sInstance;
    private FirebaseComplexResult mComplexTestResult;

    public static FirebaseSender getInstance() {
        FirebaseSender instance = sInstance;
        if (instance == null) {
            synchronized (FirebaseSender.class) {
                instance = sInstance;
                if (instance == null) {
                    sInstance = instance = new FirebaseSender();
                }
            }
        }
        return instance;
    }

    /**
     * Adds new record score to remote database if needed.
     */
    public void updateResult(@NotNull FirebaseComplexResult testResult) {
        if (mComplexTestResult != null && mComplexTestResult.equals(testResult)) return;
        else mComplexTestResult = testResult;

        Firebase fb = new Firebase(BuildConfig.FIREBASE_ROOT).child(BuildConfig.FIREBASE_GAMESCORES);
        fb = fb.push(); //new line in db
        fb.setValue(testResult);
        fb.child("timestamp").setValue(ServerValue.TIMESTAMP);

    }
}
